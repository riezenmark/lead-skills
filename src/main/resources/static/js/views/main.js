define(function(){
    return {
        view:"toolbar",
        id:"myToolbar",
        cols:[
            { view:"button", align: "left", css: "webix_transparent", width:300, type: "image", image:"images/logo.png", label: "Lead Skills"},
            { view:"button", id:"LoadBut", value:"Load", width:100, align:"left" },
            { view:"button", value:"Save", width:100, align:"center" },
            { view:"button", value:"Info", width:100, align:"right" }
        ]
    }
})