document.getElementById('itemForm').addEventListener('submit',function(event) {
    event.preventDefault();

    const item = {
        id: document.getElementById('item_id').value,

        item : document.getElementById('item_id').value,
    }

    // fetch (요청주소, 요청내용)
    // 성공
    // 실패
    fetch("/items/"+item.id+"/modify", {
        method: 'post',
        headers: {'Content-Type' : 'application/json'},
        body : JSON.stringify(item)
    }).then(response => {
        if(response.ok){
            alert('아이템이 성공적으로 변경되었습니다.');
            document.getElementById('itemForm').reset();
            window.location.href ="/items";
        } else {
            alert('아이템 생성에 실패했습니다.');
        }
    }).catch(error => {
        console.error('Error:', error);
        alert('오류가 발생했습니다.');
    });

});


