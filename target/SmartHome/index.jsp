<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset=utf-8>
    <meta name=viewport content="width=device-width,initial-scale=1,user-scalable=0">
    <title>smarthome</title>
    <link href=${pageContext.request.contextPath}/static/css/app.cd6f86aaad00ddff1b103e969b412663.css rel=stylesheet>

</head>
<body>
<div id=app-box></div>
<script>!function (e) {
    function n(r) {
        if (t[r]) return t[r].exports;
        var o = t[r] = {i: r, l: !1, exports: {}};
        return e[r].call(o.exports, o, o.exports, n), o.l = !0, o.exports
    }

    var r = window.webpackJsonp;
    window.webpackJsonp = function (t, c, a) {
        for (var i, u, f, s = 0, l = []; s < t.length; s++) u = t[s], o[u] && l.push(o[u][0]), o[u] = 0;
        for (i in c) Object.prototype.hasOwnProperty.call(c, i) && (e[i] = c[i]);
        for (r && r(t, c, a); l.length;) l.shift()();
        if (a) for (s = 0; s < a.length; s++) f = n(n.s = a[s]);
        return f
    };
    var t = {}, o = {6: 0};
    n.e = function (e) {
        function r() {
            i.onerror = i.onload = null, clearTimeout(u);
            var n = o[e];
            0 !== n && (n && n[1](new Error("Loading chunk " + e + " failed.")), o[e] = void 0)
        }

        var t = o[e];
        if (0 === t) return new Promise(function (e) {
            e()
        });
        if (t) return t[2];
        var c = new Promise(function (n, r) {
            t = o[e] = [n, r]
        });
        t[2] = c;
        var a = document.getElementsByTagName("head")[0], i = document.createElement("script");
        i.type = "text/javascript", i.charset = "utf-8", i.async = !0, i.timeout = 12e4, n.nc && i.setAttribute("nonce", n.nc), i.src = n.p + "static/js/" + e + "." + {
            0: "be38c20fdecb7a4d3e59",
            1: "ccbec781446476148f79",
            2: "421b0bb738b9cf74817e",
            3: "e227f1f95d526fdc6f3f",
            4: "72582d43fe200e4416bc",
            5: "58112d6ad2450c22229b"
        }[e] + ".js";
        var u = setTimeout(r, 12e4);
        return i.onerror = i.onload = r, a.appendChild(i), c
    }, n.m = e, n.c = t, n.d = function (e, r, t) {
        n.o(e, r) || Object.defineProperty(e, r, {configurable: !1, enumerable: !0, get: t})
    }, n.n = function (e) {
        var r = e && e.__esModule ? function () {
            return e.default
        } : function () {
            return e
        };
        return n.d(r, "a", r), r
    }, n.o = function (e, n) {
        return Object.prototype.hasOwnProperty.call(e, n)
    }, n.p = "./", n.oe = function (e) {
        throw console.error(e), e
    }
}([]);</script>
<script type=text/javascript src=${pageContext.request.contextPath}/static/js/vendor.72582d43fe200e4416bc.js></script>
<script type=text/javascript src=${pageContext.request.contextPath}/static/js/app.58112d6ad2450c22229b.js></script>
</body>
</html>
