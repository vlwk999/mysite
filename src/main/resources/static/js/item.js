document.getElementById('itemForm').addEventListener('submit',function(event) {
    event.preventDefault();

    const item = {
        item : document.getElementById('item_id').value,
    }

    // fetch (요청주소, 요청내용)
    fetch("/items", {
        method: 'post',
        headers: {'Content-Type' : 'application/json'},
        body : JSON.stringify(item)
    }).then(response => {
        if(response.ok){
            alert('아이템이 성공적으로 생성되었습니다.');
            document.getElementById('itemForm').reset();
        } else {
            alert('메시지 생성에 실패했습니다.');
        }
    })
    .catch(error => {
        console.error('Error:', error);
        alert('오류가 발생했습니다.');
    }
    );

});


