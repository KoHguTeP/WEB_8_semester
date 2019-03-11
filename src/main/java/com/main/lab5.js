function calculate() {
    var a = document.getElementById("valFirst").value;
    var b = document.getElementById("valSecond").value;
    if (a == "" || b == "") window.alert("Введите значения!");
    else {
        a = parseInt(a);
        b = parseInt(b);
        var result;
        var operation;
        var flag;

        if (document.getElementById("sum").checked) {
            result = a + b;
            operation = "+";
        }
        else if (document.getElementById("sub").checked) {
            result = a - b;
            operation = "-";
        }
        else if (document.getElementById("mul").checked) {
            result = a * b;
            operation = "*";
        }
        else if (b == 0) {
            flag = 1
            window.alert("Делить на 0 нельзя");
        }
        else {
            result = a / b;
            operation = "/";
        }

        if (!(flag == 1)) {
            window.sessionStorage.setItem("valFirst", a);
            window.sessionStorage.setItem("valSecond", b);
            window.sessionStorage.setItem("result", result);
            window.sessionStorage.setItem("operation", operation);
            document.getElementById("mainForm").action = "result.html";
        }
    }
}

function getValues() {
    document.getElementById("valFirst").innerHTML = window.sessionStorage.getItem("valFirst");
    document.getElementById("valSecond").innerHTML = window.sessionStorage.getItem("valSecond");
    document.getElementById("result").innerHTML = window.sessionStorage.getItem("result");
    document.getElementById("operation").innerHTML = window.sessionStorage.getItem("operation");
}

function check(input) {
    input.value = input.value.replace(/[^\d]/g, '');
};
