let r;

document.getElementById("R_field")
    .addEventListener('input', e => {
        check_R();
        r = inputR.value;
    })


document.getElementById("svg").onclick = function(event) {
    const rect = document.getElementById("svg").getBoundingClientRect();

    if ((r>=2 && r<=5)) {

        const x = ((event.clientX - rect.left -125)/(18*5)*r);
        const y = (((- event.clientY) + rect.bottom -125)/(18*5)*r);
        let xt = (event.clientX - rect.left);
        let yt = (( event.clientY) - rect.top );

        changePoint(xt,yt)
        let inputY = document.getElementById("Y_field");
        inputY.value = y.toString() ;
        let inputX = document.getElementById("X_field");
        inputX.value = x.toString() ;
        $("#submit2").click()

    } else {
        alert("Point can't be verified make sure that R value is set")
    }
}


function changePoint(x,y) {
    let n = document.getElementById("table_out").getElementsByTagName("tr").length
    let point = $("#point");
    point.attr({
        cx: x,
        cy: y,
        visibility: "visible"
    });
}


function clearTable() {
    $("#table_out").empty();
}