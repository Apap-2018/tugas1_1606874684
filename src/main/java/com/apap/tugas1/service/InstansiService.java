package com.apap.tugas1.service;

import java.util.List;

import com.apap.tugas1.model.InstansiModel;
import com.apap.tugas1.model.ProvinsiModel;

public interface InstansiService {
	InstansiModel getInstansiDetailById(long id);
	List<InstansiModel> getAllInstansi();
	List<InstansiModel> getInstansiFromProvinsi(ProvinsiModel provinsi);
//	InstansiModel getIdByName(String name);
}
