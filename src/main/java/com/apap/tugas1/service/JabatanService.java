package com.apap.tugas1.service;

import java.util.List;

import com.apap.tugas1.model.JabatanModel;

public interface JabatanService {
	JabatanModel getJabatanDetailById(long id);
	List<JabatanModel> getAllJabatan();
	
	void addJabatan(JabatanModel jabatan);
	void updateJabatan(long id, JabatanModel jabatan);
	void deleteJabatan(long id);
}
