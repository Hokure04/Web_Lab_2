function create_get(){

    const xhr = new XMLHttpRequest();

    let url = new URL('http://localhost:8080/Web_Lab_JSP_2/CServlet');

    url.searchParams.set('y',value_Y);
    url.searchParams.set('r',value_R);

    xhr.onreadystatechange = function () {
        if (xhr.readyState === XMLHttpRequest.DONE) {

        }
    }
    xhr.open('GET', url);
    xhr.send();

}