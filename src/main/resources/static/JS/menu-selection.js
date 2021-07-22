window.onload = (ev => {
    let prevLabel = null;
    const menuInputs = document.getElementsByName("operation");

    menuInputs.forEach(chBox => {
        chBox.onchange = () => {
            if (chBox.checked){
                if(prevLabel != null){
                    prevLabel.style.color = "black";
                }

                //todo: normal switch with classes
                const label = chBox.previousElementSibling;
                label.style.color = "#d0512d";
                prevLabel = label;
            }
        }
    })
})