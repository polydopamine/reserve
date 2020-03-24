var _instrument = {
    add : function(formData, resolve, reject){
        _common.request({
            url     : _common.getServerUrl('/admin/inst/add.do'),
            data    : formData,
            method  : 'POST',
            success : resolve,
            error   : reject
        });
    },
    update : function(formData, resolve, reject){
        _common.request({
            url     : _common.getServerUrl('/admin/inst/update.do'),
            data    : formData,
            method  : 'POST',
            success : resolve,
            error   : reject
        });
    }
};