requirejs.config({
    baseUrl: "js"
})

function buildRoute(view) {
    return function() {
        webix.ui({
            id: 'root',
            rows: [
                view
            ]
        }, $$('root'))
    }
}

require(['views/main'], function(main) {
    webix.ready(function() {
        webix.ui({
            id: "root",
            container: "app"
        })
    })

    routie({
        "": buildRoute(main)
    })
})
