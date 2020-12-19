webix.ready(function () {
    webix.ui({
        type: "line",
        container: "app",
        rows: [
            {template: "Row 1"},
            {template: "Row 2"}
        ]
    });
})