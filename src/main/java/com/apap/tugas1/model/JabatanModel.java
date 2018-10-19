package com.apap.tugas1.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "jabatan")
public class JabatanModel implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotNull
	@Size(max = 255)
	@Column(name = "nama", nullable = false)
	private String nama;

	@NotNull
	@Size(max = 255)
	@Column(name = "deskripsi", nullable = false)
	private String deskripsi;

	@NotNull
	@Column(name = "gaji_pokok", nullable = false)
	private Double gaji_pokok;

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "jabatan")
	private List<PegawaiModel> pegawai;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public String getDeskripsi() {
		return deskripsi;
	}

	public void setDeskripsi(String deskripsi) {
		this.deskripsi = deskripsi;
	}

	public Double getGaji_pokok() {
		return gaji_pokok;
	}

	public void setGaji_pokok(Double gaji_pokok) {
		this.gaji_pokok = gaji_pokok;
	}

	public List<PegawaiModel> getPegawai() {
		return pegawai;
	}

	public void setPegawai(List<PegawaiModel> pegawai) {
		this.pegawai = pegawai;
	}
	
	public int jumlahPegawai() {
		return this.getPegawai().size();
	}

}
