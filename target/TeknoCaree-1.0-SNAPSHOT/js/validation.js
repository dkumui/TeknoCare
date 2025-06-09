function validasiFormPelanggan() {
    let isValid = true;
    const nama = document.getElementById('nama');
    const kontak = document.getElementById('kontak');
    const namaError = document.getElementById('namaError');
    const kontakError = document.getElementById('kontakError');

    nama.classList.remove('is-invalid');
    kontak.classList.remove('is-invalid');
    if(namaError) namaError.style.display = 'none';
    if(kontakError) kontakError.style.display = 'none';


    if (nama.value.trim() === '') {
        nama.classList.add('is-invalid');
        if(namaError) namaError.style.display = 'block';
        isValid = false;
    }

    const kontakValue = kontak.value.trim();
    if (kontakValue === '') {
        kontak.classList.add('is-invalid');
        if(kontakError) {
            kontakError.textContent = 'Kontak tidak boleh kosong.';
            kontakError.style.display = 'block';
        }
        isValid = false;
    } else if (!/^[0-9]+$/.test(kontakValue) && !kontakValue.includes('@')) {
        kontak.classList.add('is-invalid');
         if(kontakError) {
            kontakError.textContent = 'Format kontak tidak valid. Masukkan No. HP (hanya angka) atau Email (mengandung @).';
            kontakError.style.display = 'block';
        }
        isValid = false;
    }
    
    if (!isValid) {
        // Fokus ke field pertama yang error
        const firstInvalidField = document.querySelector('.is-invalid');
        if (firstInvalidField) {
            firstInvalidField.focus();
        }
    }
    return isValid;
}

function validasiFormServis() {
    let isValid = true;
    const kerusakan = document.getElementById('kerusakan');
    const biaya = document.getElementById('biaya');
    const kerusakanError = document.getElementById('kerusakanError');
    const biayaError = document.getElementById('biayaError');

    // Reset error
    kerusakan.classList.remove('is-invalid');
    biaya.classList.remove('is-invalid');
    if(kerusakanError) kerusakanError.style.display = 'none';
    if(biayaError) biayaError.style.display = 'none';

    if (kerusakan.value.trim() === '') {
        kerusakan.classList.add('is-invalid');
        if(kerusakanError) kerusakanError.style.display = 'block';
        isValid = false;
    }

    const biayaValue = biaya.value.trim();
    if (biayaValue === '' || parseFloat(biayaValue) < 0 || isNaN(parseFloat(biayaValue))) {
        biaya.classList.add('is-invalid');
        if(biayaError) biayaError.style.display = 'block';
        isValid = false;
    }
    
    if (!isValid) {
        const firstInvalidField = document.querySelector('.is-invalid');
        if (firstInvalidField) {
            firstInvalidField.focus();
        }
    }
    return isValid;
}