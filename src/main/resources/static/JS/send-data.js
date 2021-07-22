const inputForm = document.getElementById("input_form");

inputForm.addEventListener("submit", async (event) => {
    event.preventDefault();

    function getOp() {
        const allOperations = document.getElementsByName('operation');

        for (let i = 0; i < allOperations.length; i++) {
            const opElement = allOperations[i];
            if (opElement.checked)
                return opElement.value;
        }

        return null;
    }

    async function sendDataBody(data, url) {
        const xhr = new XMLHttpRequest();
        xhr.open('POST', url);
        xhr.setRequestHeader('Content-Type', 'application/json');
        xhr.responseType = 'json';

        xhr.send(data);

        xhr.onloadend = (event) => {
            const resp = xhr.response;
            console.log(`resp is ${(resp)}`)
            console.log(`resp is ${JSON.stringify(resp)}`)

            return JSON.stringify(resp)
        };
    }

    async function sendDataParam(data, url) {

    }

    const formData = new FormData(inputForm);
    const op = getOp();
    const data = formData.get('input');
    console.log(`input is ${data}`)
    let response = null;
    switch (op) {
        case 'all':
            response = await sendDataParam(data, `http://localhost:3000/math/calculate/`);
            break;

        case 'avg':
        case 'min':
        case 'max':
        case 'sum':
            response = await sendDataBody(data, `http://localhost:3000/math/calculate/${op}`);
            break;

        default:
            alert('operation not found')
            break;
    }
});