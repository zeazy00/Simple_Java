window.onload = (ev => {
    let prevLabel = null;
    const menuInputs = document.getElementsByName("operation");

    menuInputs.forEach(chBox => {
        chBox.onchange = () => {
            if (chBox.checked){
                if(prevLabel != null){
                    prevLabel.style.color = "black"
                }

                const label = chBox.previousElementSibling;
                label.style.color = "#457b9d";
                prevLabel = label;
            }
        }
    })
})