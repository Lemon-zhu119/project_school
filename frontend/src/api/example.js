import request from '@/utils/request'

export function getAll(type) {
    return request({
        url: type + '/all',
        method: 'get',
    })
}

export function delOne(index, type) {
    return request({
        url: type + '/deleteOne',
        method: 'post',
        params: {
            index: index
        }
    })
}
export function findOne(index, type) {
    return request({
        url: type + '/findOne',
        method: 'post',
        params: {
            index: index
        }
    })
}
export function findById(index, type) {
    return request({
        url: type + '/findById',
        method: 'post',
        params: {
            index: index
        }
    })
}
export function updateOne(form, type) {
    var user = JSON.stringify(form);
    return request({
        url: type + '/updateOne',
        method: 'post',
        data: user,
        headers: {
            "Content-Type": "application/json"
        }
    })
}

export function queryOne(formInline, type) {
    var user = JSON.stringify(formInline);
    return request({
        url: type + '/queryOne',
        method: 'post',
        data: user,
        headers: {
            "Content-Type": "application/json"
        }
    })
}

export function addOne(form, type) {
    var user = JSON.stringify(form);
    return request({
        url: type + '/insertOne',
        method: 'post',
        data: user,
        headers: {
            "Content-Type": "application/json"
        }
    })
}
export function signInTody(date) {
    return request({
        url: "date/signin",
        method: "post",
        data: date,
        headers: {
            "Content-Type": "application/json"
        }
    })
}
export function signedInTody(date) {
    return request({
        url: "date/signedin",
        method: "post",
        data: date,
        headers: {
            "Content-Type": "application/json"
        }
    })
}