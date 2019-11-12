package org.bedu.testing.demo.monitoreo.constants;

import java.util.Arrays;
import java.util.List;

import org.bedu.testing.demo.monitoreo.entitys.Perfil;

import lombok.experimental.UtilityClass;

public class MonitoreoProfiles {

	public static final List<Long> MONITOREO_PROFILES = Collections.unmodifiableList(Arrays.asList(Perfil.ADMIN_ID,
			Perfil.OPERATIVO_ID));

	public static final List<Long> PROMOTORES = Collections.unmodifiableList(Arrays.asList(Perfil.PROMOTOR_ID));

	public static boolean isMarchandProfile(Long id) {
		return MONITOREO_PROFILES.contains(id);
	}

}
