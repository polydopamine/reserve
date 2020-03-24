var _reserve = {
    delete : function(inservationId, resolve, reject){
        _common.request({
            url     : _common.getServerUrl('/reserve/delete.do'),
            data    : inservationId,
            method  : 'GET',
            success : resolve,
            error   : reject
        });
    },
    edit : function(formData, resolve, reject){
        _common.request({
            url     : _common.getServerUrl('/reserve/update.do'),
            data    : formData,
            method  : 'GET',
            success : resolve,
            error   : reject
        });
    },
    add : function(formData, resolve, reject){
        _common.request({
            url     : _common.getServerUrl('/reserve/add.do'),
            data    : formData,
            method  : 'GET',
            success : resolve,
            error   : reject
        });
    }
};