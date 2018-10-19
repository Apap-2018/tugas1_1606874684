package com.apap.tugas1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apap.tugas1.model.JabatanModel;
import com.apap.tugas1.repository.JabatanDB;

@Service
@Transactional
public class JabatanServiceImpl implements JabatanService{
	@Autowired
	private JabatanDB jabatanDb;
	
	@Override
	public void addJabatan(JabatanModel jabatan) {
		jabatanDb.save(jabatan);
	}
	
	@Override
	public JabatanModel getJabatanDetailById(long id) {
		return jabatanDb.findById(id);
	}
	
	@Override
	public List<JabatanModel> getAllJabatan(){
		return jabatanDb.findAll();
	}
	
	@Override
	public void updateJabatan(long id, JabatanModel jabatan) {
		JabatanModel jabatanChange = jabatanDb.findById(id);
		jabatanChange.setNama(jabatan.getNama());
		jabatanChange.setDeskripsi(jabatan.getDeskripsi());
		jabatanChange.setGaji_pokok(jabatan.getGaji_pokok());
		jabatanDb.save(jabatanChange);
	}
	
	@Override
	public void deleteJabatan(long id) {
		jabatanDb.deleteById(id);
	}
}
