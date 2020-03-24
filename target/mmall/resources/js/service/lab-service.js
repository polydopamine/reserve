var _lab = {
    add : function(formData, resolve, reject){
        _common.request({
            url     : _common.getServerUrl('/admin/lab/add.do'),
            data    : formData,
            method  : 'POST',
            success : resolve,
            error   : reject
        });
    }
};