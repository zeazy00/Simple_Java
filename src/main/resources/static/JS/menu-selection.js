window.onload = (ev => {
    let prevLabel = null;
    const menuInputs = document.getElementsByName("operation");

    menuInputs.forEach(chBox => {
        chBox.onchange = () => {
            if (chBox.checked){
                if(prevLabel != null){
                    prevLabel.style.color = "black";
                }
                //todo: create normal style with shadow
                const label = chBox.previousElementSibling;
                label.style.color = "#d0512d";
                prevLabel = label;
            }
        }
    })
})