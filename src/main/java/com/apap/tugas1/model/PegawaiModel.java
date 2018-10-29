package com.apap.tugas1.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.apap.tugas1.model.InstansiModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "pegawai")
public class PegawaiModel implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotNull
	@Size(max = 255)
	@Column(name = "nip", nullable = false, unique = true)
	private String nip;
	
	@NotNull
	@Size(max = 255)
	@Column(name = "nama", nullable = false)
	private String nama;
	
	@NotNull
	@Size(max = 255)
	@Column(name = "tempat_lahir", nullable = false)
	private String tempat_lahir;
	
	@NotNull
	@Column(name = "tanggalLahir", nullable = false)
	private Date tanggalLahir;
	
	@NotNull
	@Size(max = 255)
	@Column(name = "tahunMasuk", nullable = false)
	private String tahunMasuk;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_instansi", referencedColumnName = "id", nullable = false)
	@OnDelete(action = OnDeleteAction.NO_ACTION)
	@JsonIgnore
	private InstansiModel instansi;
	
	@JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "jabatan_pegawai",
            joinColumns = {@JoinColumn(name = "id_pegawai")},
            inverseJoinColumns = {@JoinColumn(name = "id_jabatan")})
    private List<JabatanModel> jabatan;

	@OneToMany(mappedBy = "pegawai", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<JabatanPegawaiModel> jabatanPegawaiList;
	
	public JabatanModel getJabatanTerbesar() {
		JabatanModel jabatanFix = jabatan.get(0);
		for(int i=0; i<jabatan.size(); i++) {
			if(jabatan.get(i).getGaji_pokok()>jabatanFix.getGaji_pokok()) {
				jabatanFix=jabatan.get(i);
			}
			jabatanFix=jabatan.get(i);
		}
		return jabatanFix;
	}
	
	public List<JabatanPegawaiModel> getJabatanPegawaiList() {
		return jabatanPegawaiList;
	}

	public void setJabatanPegawaiList(List<JabatanPegawaiModel> jabatanPegawaiList) {
		this.jabatanPegawaiList = jabatanPegawaiList;
	}

	public Double gajiTotal() {
		Double gajiPokok = this.getJabatanTerbesar().getGaji_pokok();
		Double tunjangan = this.getInstansi().getProvinsi().getPresentase_tunjangan();
		return gajiPokok+(gajiPokok*tunjangan);
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNip() {
		return nip;
	}

	public void setNip(String nip) {
		this.nip = nip;
	}

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public String getTempat_lahir() {
		return tempat_lahir;
	}

	public void setTempat_lahir(String tempat_lahir) {
		this.tempat_lahir = tempat_lahir;
	}

	public Date getTanggalLahir() {
		return tanggalLahir;
	}

	public void setTanggalLahir(Date tanggalLahir) {
		this.tanggalLahir = tanggalLahir;
	}

	public String getTahunMasuk() {
		return tahunMasuk;
	}

	public void setTahunMasuk(String tahunMasuk) {
		this.tahunMasuk = tahunMasuk;
	}

	public InstansiModel getInstansi() {
		return instansi;
	}

	public void setInstansi(InstansiModel instansi) {
		this.instansi = instansi;
	}

	public List<JabatanModel> getJabatan() {
		return jabatan;
	}

	public void setJabatan(List<JabatanModel> jabatan) {
		this.jabatan = jabatan;
	}
	
}
