import request from "@/utils/request";
//登入
export function login(form) {
    var user = JSON.stringify(form);
    return request({
        url: "/user/login",
        method: "post",
        data: user,
        headers: {
            "Content-Type": "application/json"
        }
    });
}

// 用户(登录者) 信息
export function getInfo() {
    return request({
        url: "/user/info",
        method: "get"
            // params: { token }
    });
}

//登出
export function logout(username) {
    return request({
        url: "/user/logout",
        method: "post",
        params: {
            username
        }
    });
}

export function user_statics() {
    return request({
        url: "/user/statics",
        method: "post"
    });
}

//获取所有用户数据
export function getList(params) {
    return request({
        url: "/user/users",
        method: "get",
        params
    });
}

//删除用户数据
export function delUser(index) {
    return request({
        url: "/user/delete",
        method: "post",
        params: {
            index: index
        }
    });
}

//更新用户数据
export function updateUser(form) {
    var user = JSON.stringify(form);
    return request({
        url: "/user/update",
        method: "post",
        data: user,
        headers: {
            "Content-Type": "application/json"
        }
    });
}

//查询某位用户的数据
export function queryUser(formInline) {
    var user = JSON.stringify(formInline);
    return request({
        url: "/user/query",
        method: "post",
        data: user,
        headers: {
            "Content-Type": "application/json"
        }
    });
}
export function findUser(formInline) {
    var user = JSON.stringify(formInline);
    return request({
        url: "/user/findOne",
        method: "post",
        data: user,
        headers: {
            "Content-Type": "application/json"
        }
    });
}
export function ChangePassword(formInline,type) {
    var user = JSON.stringify(formInline);
    return request({
        url: type+"/changePassword",
        method: "post",
        data: user,
        headers: {
            "Content-Type": "application/json"
        }
    });
}

//添加某位用户的数据
export function addUser(form) {
    var user = JSON.stringify(form);
    return request({
        url: "/user/insert",
        method: "post",
        data: user,
        headers: {
            "Content-Type": "application/json"
        }
    });
}

//获取首页卡片数据
export function getCard(type) {
    return request({
        url: "/statistic/card",
        method: "get",
        params: {
            type
        }
    })
}

//获取卡片所有数据(两周)
export function getCardList(type) {
    return request({
        url: "/statistic/list",
        method: "get",
        params: {
            type
        }
    })
}