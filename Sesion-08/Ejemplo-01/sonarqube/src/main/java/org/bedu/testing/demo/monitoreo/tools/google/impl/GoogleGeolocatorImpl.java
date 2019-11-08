package org.bedu.testing.demo.monitoreo.tools.google.impl;

import java.io.IOException;
import java.math.BigDecimal;

import org.bedu.testing.demo.monitoreo.entitys.Colonia;
import org.bedu.testing.demo.monitoreo.entitys.Establecimiento;
import org.bedu.testing.demo.monitoreo.entitys.Estado;
import org.bedu.testing.demo.monitoreo.entitys.Municipio;
import org.bedu.testing.demo.monitoreo.tools.google.GoogleGeolocator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.ComponentFilter;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.Geometry;
import com.google.maps.model.LatLng;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@Slf4j
public class GoogleGeolocatorImpl implements GoogleGeolocator {

    private final GeoApiContext geoApiContext;

    @Qualifier("countryFilter")
    private final ComponentFilter countryComponentFilter;

    @Qualifier("defaultSW")
    private final LatLng defaultSW;

    @Qualifier("defaultNE")
    private final LatLng defaultNE;

    @Override
    public Establecimiento geolocate(Establecimiento establecimiento) throws InterruptedException{

        log.debug("Buscando coordenadas del establecimiento [{}] sin limites definidos",
                establecimiento.getNombreEstablecimiento());

        return geolocate(establecimiento, defaultSW, defaultNE);
    }

    private void loadCoordinates(Establecimiento establecimiento, GeocodingResult results) {
        Geometry geometry = results.geometry;

        establecimiento.setLatitud(BigDecimal.valueOf(geometry.location.lat));
        establecimiento.setLongitud(BigDecimal.valueOf(geometry.location.lng));
    }

    private String buildAddress(Establecimiento establecimiento) {
        Colonia c = establecimiento.getColonia();
        Municipio m = c.getMunicipio();
        Estado e = m.getEstado();
        String cp = c.getCompositeKey().getCodigoPostal();

        String address = String.format("%s %s %s %s, CP %s", establecimiento.getCalleYNumero(), c.getDescripcion(),
                m.getDescripcion(), e.getDescripcion(), cp);

        log.debug("Dirección: [{}]", address);

        return address;
    }

    private Establecimiento geolocate(Establecimiento establecimiento, LatLng limiteSW, LatLng limiteNE) throws InterruptedException {

        String address = buildAddress(establecimiento);

        GeocodingResult[] results = null;
        GeocodingResult bestMatch = null;

        try {
            //@formatter:off
            results = GeocodingApi.geocode(geoApiContext, address)
                    .components(ComponentFilter.country("MX"))
                    .bounds(limiteSW, limiteNE)
                    .await();
            //@formatter:on
        } catch (InterruptedException e) {
            log.error(String.format("Error al recuperar coordenadas de la dirección [%s]", address), e);
            throw e;
        } catch (ApiException | IOException e1) {
            log.error(String.format("Error al recuperar coordenadas de la dirección [%s]", address), e1);
            return establecimiento;

        }

        if (results.length < 1) {
            log.error(String.format("No se encontraron resultados para la dirección [%s]", address));
            return establecimiento;
        } else if (results.length > 1) {
            log.warn("Se encontraron múltiples resultados para la dirección [{}]. Se toma sólo el primer valor",
                    address);
            log.warn("Resultados: [{}]", (Object[]) results);
            //TODO: Define a bestmatch strategy
            bestMatch = results[0];

        } else {
            bestMatch = results[0];
        }

        loadCoordinates(establecimiento, bestMatch);
        log.debug("Coordenadas [{},{}]", establecimiento.getLatitud(), establecimiento.getLongitud());

        return establecimiento;

    }

    @Override
    public Establecimiento geolocate(Establecimiento establecimiento, Double latSW, Double lngSW, Double latNE,
                                     Double lngNE) throws InterruptedException{

        log.debug("Buscando coordenadas del establecimiento [{}] con límites definidos",
                establecimiento.getNombreEstablecimiento());

        LatLng sw = new LatLng(latSW, lngSW);
        LatLng ne = new LatLng(latNE, lngNE);

        return geolocate(establecimiento, sw, ne);

    }

}
