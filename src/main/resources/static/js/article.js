// 게시글 삭제 기능
const deleteButton = document.getElementById('delete-btn');

if (deleteButton) {
    deleteButton.addEventListener('click', event => {
        let id = document.getElementById('article-id').value;
        fetch(`/api/articles/${id}`, {
            method: 'DELETE' // HTTP 메서드 DELETE로 해당 url을 전송한다.
        })
            .then(() => {
                alert("삭제가 완료되었습니다.");
                location.replace('/articles');
            });
    });
}