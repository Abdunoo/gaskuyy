package com.abdun.error;

import org.jboss.logging.Logger;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

/**
 *
 * @author amrullah
 */
public enum ErrorCode {
	/**
	 * This is generic error code. It accept a string for the error message. Since it is generic, it will not be translated in client side.
	 * You can use it this way:
	 * throw ErrorCode.ER0000.exception("Pesan error generic.");
	 */
	ER0000("%s"),
	ER0001("This is an example of error message. If you choose other language, this error is also translated."),
	ER0002("Error Message With Parameters. Name: %s. Age: %s"),
	ER0003("Old password doesn't match"),
	ER0004("Old password can't be empty!"),
	ER0005("Email already exists, please use another one"),
	ER0006("Can't delete admin account"),
	ER0007("Tidak bisa mengubah akun Anda sendiri."),
	ER0008("Can't change password of username hellocountry!"),
	ER0009("%s already used, please use another"),
	ER0010("Invalid search criteria [%s : %s]"),
	/**
	 * Your data has been updated by other user. Please refresh!
	 */
	ER0100("Your data has been updated by other user. Please refresh!"),
	ER0400("%s"),
	/**
	 * You are not authenticated. Please login.
	 */
	ER0401("You are not authenticated. Please login."),
	/**
	 * You don't have access to the requested action. Please contact
	 * Administrator.
	 */
	ER0403("Anda tidak memiliki hak akses. Silahkan kontak administrator."),
	
	
	ER1001("Transaksi harus memiliki jurnal detail."),
	ER1002("Nilai Debit tidak sama dengan Credit. Debit %s | Credit %s."),
	ER1003("Nilai Debit/Credit harus positive."),
	ER1004("Akun \"%s\" ada di sisi Debit dan Kredit."),
	ER1005("Transaksi %s untuk akun '%s' hanya bisa dilakukan oleh %s"),
	ER1006("Tanggal transaksi harus sesudah %s"),
	ER1007("Tanggal transaksi yang bisa diedit maksimal %s"),
	ER1008("Untuk transaksi baru, nilai %s harus lebih besar dari 0"),
	ER1009("Gagal membuat organisasi baru. Maksimal organisasi yang Anda miliki adalah <b>%s</b>.<br>Silakan hapus organisasi yang sudah ada,<br>atau hubungi support Pembukuan untuk  meningkatkan limit Anda."),
	ER1010("Sudah ada Akun dengan nama <b>%s</b>.<br>Silahkan menggunakan nama yang lain.</br>"),
	ER1011("Sudah ada akun <b>%s</b>. Silahkan ganti nama akun."),
	ER1012("Gagal menghapus \"%s\". Akun ini sudah terpakai untuk transaksi. Silahkan non-aktifkan jika tidak lagi dipakai."),
	ER1013("Akun untuk pengelolaan \"%s\" belum diseting."),
	ER1101("Gagal membuat tenant baru. Silahkan kontak team support. Script error code %s"),
	/**
	 * Link verifikasi untuk email %s sudah kadaluarsa
	 */
	ER1102("Link verifikasi untuk email %s sudah kadaluarsa."),

	/**
	 * Tracker related error
	 */
	ER1200("Aset #%s sudah disusutkan untuk periode sekarang"),
	ER1201("Nilai penyusutan terlalu besar. Maksimal %s"),
	ER1202("Data pengelolaan asset ID %s tidak valid")
	;

	private final String message;
	private static Logger log = Logger.getLogger(ErrorCode.class);
	private ErrorCode(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
	
	public ErrorEntity create(String... parameters) {
		ErrorEntity ee = new ErrorEntity(this, parameters);
		return ee;
	}
	
	public KnownException exception(String... parameters) {
		ErrorEntity errorEntity = this.create(parameters);
		log.error(errorEntity.getMessage());
		final KnownException badRequestException = new KnownException(Response.status(Response.Status.BAD_REQUEST)
				  .entity(errorEntity).type(MediaType.APPLICATION_JSON).build());
		return badRequestException;
		
	}

	@Override
	public String toString() {
		return "Error " + this.name() + " message=" + message + '}';
	}
	
	
}
