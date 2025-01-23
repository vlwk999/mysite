// 공백 검사
function hasWhiteSpace(str) {
    return /\s/.test(str)
}
// 특수문자 검사 [!@#$%]
// ., -, \는 정규시에 사용되는 문자이므로 이스케이프(escape) 처리를 해야 합니다.
function hasSpecialChar(str) {
    return /[!@#$%^&*():{}|<>,'~_=\.\-\\`]/.test(str);
}
// 숫자로 시작하는지 검사
function startWithNumber(str) {
    // 1abc, 9abc 시작할 때 검사는 ^를 사용합니다.
    return /^[0-9]/.test(str)
}
document.getElementById('name_id').addEventListener('input', function(e) {
    const value = e.target.value;
    const spaceError = document.getElementById('spaceError');
    const specialCharError = document.getElementById('specialCharError');
    const startWithNumberError = document.getElementById('startWithNumberError');
    //console.log(value, "=>", hasWhiteSpace(value));
    spaceError.style.display = hasWhiteSpace(value) ? 'block' : 'none';
    specialCharError.style.display = hasSpecialChar(value) ? 'block' : 'none';
    startWithNumberError.style.display = startWithNumber(value) ? 'block' : 'none';
})
document.getElementById('supplyForm').addEventListener('submit', function(e) {
    e.preventDefault();
    const supply = {
        name: document.getElementById('name_id').value,
        contact1: document.getElementById('contact1_id').value,
        contact2: document.getElementById('contact2_id').value,
        businessNumber: document.getElementById('businessNumber_id').value,
    }
    if (! hasWhiteSpace(supply.name) &&
        ! hasSpecialChar(supply.name) &&
        ! startWithNumber(supply.name)) {
            alert('서버로 전송한다.');
    } else {
        alert('입력값을 다시 확인해주세요.');
    }
    // fetch(요청주소, 요청내용객체)
    // 성공
    // 실패
    fetch("/supply", {
        method: 'post',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify(supply)
    }).then(response => {
        if (response.ok) {
            alert('구입처가 성공적으로 생성되었습니다.');
            document.getElementById('supplyForm').reset();
        } else {
            alert('구입처 생성에 실패했습니다.');
        }
    }).catch(error => {
        console.error('Error:', error);
        alert('오류가 발생했습니다.');
    });
});