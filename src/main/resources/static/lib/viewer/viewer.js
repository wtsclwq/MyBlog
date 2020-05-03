/*!
 * Viewer.js v0.3.1
 * https://github.com/fengyuanchen/viewerjs
 *
 * Copyright (c) 2015-2016 Fengyuan Chen
 * Released under the MIT license
 *
 * Date: 2016-02-02T11:35:52.542Z
 */
!function(e, t) {
    "object" == typeof module && "object" == typeof module.exports ? module.exports = e.document ? t(e, !0) : function(e) {
        if (!e.document)
            throw new Error("Viewer requires a window with a document");
        return t(e)
    }
    : t(e)
}("undefined" != typeof window ? window : this, function(e, t) {
    "use strict";
    function i(e) {
        return Ae.call(e).slice(8, -1).toLowerCase()
    }
    function n(e) {
        return "string" == typeof e
    }
    function a(e) {
        return "number" == typeof e && !isNaN(e)
    }
    function o(e) {
        return "undefined" == typeof e
    }
    function r(e) {
        return "object" == typeof e && null !== e
    }
    function s(e) {
        var t, i;
        if (!r(e))
            return !1;
        try {
            return t = e.constructor,
            i = t.prototype,
            t && i && Ce.call(i, "isPrototypeOf")
        } catch (n) {
            return !1
        }
    }
    function l(e) {
        return "function" === i(e)
    }
    function u(e) {
        return Array.isArray ? Array.isArray(e) : "array" === i(e)
    }
    function c(e, t) {
        return t = t >= 0 ? t : 0,
        Array.from ? Array.from(e).slice(t) : _e.call(e, t)
    }
    function d(e, t) {
        var i = -1;
        return t.indexOf ? t.indexOf(e) : (f(t, function(t, n) {
            return t === e ? (i = n,
            !1) : void 0
        }),
        i)
    }
    function m(e) {
        return n(e) && (e = e.trim ? e.trim() : e.replace(Le, "1")),
        e
    }
    function f(e, t) {
        var i, n;
        if (e && l(t))
            if (u(e) || a(e.length))
                for (n = 0,
                i = e.length; i > n && t.call(e, e[n], n, e) !== !1; n++)
                    ;
            else if (r(e))
                for (n in e)
                    if (e.hasOwnProperty(n) && t.call(e, e[n], n, e) === !1)
                        break;
        return e
    }
    function h(e) {
        var t;
        if (arguments.length > 1) {
            if (t = c(arguments),
            Object.assign)
                return Object.assign.apply(Object, t);
            t.shift(),
            f(t, function(t) {
                f(t, function(t, i) {
                    e[i] = t
                })
            })
        }
        return e
    }
    function v(e, t) {
        var i = c(arguments, 2);
        return function() {
            return e.apply(t, i.concat(c(arguments)))
        }
    }
    function g(e, t) {
        var i = e.style;
        f(t, function(e, t) {
            Ie.test(t) && a(e) && (e += "px"),
            i[t] = e
        })
    }
    function w(t) {
        return e.getComputedStyle ? e.getComputedStyle(t, null) : t.currentStyle
    }
    function p(e, t) {
        return e.classList ? e.classList.contains(t) : e.className.indexOf(t) > -1
    }
    function b(e, t) {
        var i;
        if (t) {
            if (a(e.length))
                return f(e, function(e) {
                    b(e, t)
                });
            if (e.classList)
                return e.classList.add(t);
            i = m(e.className),
            i ? i.indexOf(t) < 0 && (e.className = i + " " + t) : e.className = t
        }
    }
    function y(e, t) {
        return t ? a(e.length) ? f(e, function(e) {
            y(e, t)
        }) : e.classList ? e.classList.remove(t) : void (e.className.indexOf(t) >= 0 && (e.className = e.className.replace(t, ""))) : void 0
    }
    function x(e, t, i) {
        return a(e.length) ? f(e, function(e) {
            x(e, t, i)
        }) : void (i ? b(e, t) : y(e, t))
    }
    function z(e, t) {
        return r(e[t]) ? e[t] : e.dataset ? e.dataset[t] : e.getAttribute("data-" + t)
    }
    function D(e, t, i) {
        r(i) && o(e[t]) ? e[t] = i : e.dataset ? e.dataset[t] = i : e.setAttribute("data-" + t, i)
    }
    function E(e, t) {
        r(e[t]) ? delete e[t] : e.dataset ? delete e.dataset[t] : e.removeAttribute("data-" + t)
    }
    function k(e, t, i, n) {
        var a = m(t).split(Te)
          , o = i;
        return a.length > 1 ? f(a, function(t) {
            k(e, t, i)
        }) : (n && (i = function() {
            return I(e, t, i),
            o.apply(e, arguments)
        }
        ),
        void (e.addEventListener ? e.addEventListener(t, i, !1) : e.attachEvent && e.attachEvent("on" + t, i)))
    }
    function I(e, t, i) {
        var n = m(t).split(Te);
        return n.length > 1 ? f(n, function(t) {
            I(e, t, i)
        }) : void (e.removeEventListener ? e.removeEventListener(t, i, !1) : e.detachEvent && e.detachEvent("on" + t, i))
    }
    function L(e, t) {
        var i;
        return e.dispatchEvent ? (l(H) ? i = new H(t,{
            bubbles: !0,
            cancelable: !0
        }) : (i = W.createEvent("Event"),
        i.initEvent(t, !0, !0)),
        e.dispatchEvent(i)) : e.fireEvent ? e.fireEvent("on" + t) : void 0
    }
    function T(e) {
        e.preventDefault ? e.preventDefault() : e.returnValue = !1
    }
    function Y(t) {
        var i, n = t || e.event;
        return n.target || (n.target = n.srcElement || W),
        a(n.pageX) || (i = W.documentElement,
        n.pageX = n.clientX + (e.scrollX || i && i.scrollLeft || 0) - (i && i.clientLeft || 0),
        n.pageY = n.clientY + (e.scrollY || i && i.scrollTop || 0) - (i && i.clientTop || 0)),
        n
    }
    function F(t) {
        var i = W.documentElement
          , n = t.getBoundingClientRect();
        return {
            left: n.left + (e.scrollX || i && i.scrollLeft || 0) - (i && i.clientLeft || 0),
            top: n.top + (e.scrollY || i && i.scrollTop || 0) - (i && i.clientTop || 0)
        }
    }
    function X(e) {
        var t = e.length
          , i = 0
          , n = 0;
        return t && (f(e, function(e) {
            i += e.pageX,
            n += e.pageY
        }),
        i /= t,
        n /= t),
        {
            pageX: i,
            pageY: n
        }
    }
    function S(e, t) {
        return e.getElementsByTagName(t)
    }
    function V(e, t) {
        return e.getElementsByClassName ? e.getElementsByClassName(t) : e.querySelectorAll("." + t)
    }
    function N(e, t) {
        return t.length ? f(t, function(t) {
            N(e, t)
        }) : void e.appendChild(t)
    }
    function P(e) {
        e.parentNode && e.parentNode.removeChild(e)
    }
    function A(e) {
        for (; e.firstChild; )
            e.removeChild(e.firstChild)
    }
    function C(e, t) {
        o(e.textContent) ? e.innerText = t : e.textContent = t
    }
    function _(e) {
        return e.offsetWidth
    }
    function R(e) {
        return n(e) ? e.replace(/^.*\//, "").replace(/[\?&#].*$/, "") : ""
    }
    function q(e, t) {
        var i;
        return e.naturalWidth ? t(e.naturalWidth, e.naturalHeight) : (i = W.createElement("img"),
        i.onload = function() {
            t(this.width, this.height)
        }
        ,
        void (i.src = e.src))
    }
    function M(e) {
        var t = []
          , i = e.rotate
          , n = e.scaleX
          , o = e.scaleY;
        return a(i) && t.push("rotate(" + i + "deg)"),
        a(n) && a(o) && t.push("scale(" + n + "," + o + ")"),
        t.length ? t.join(" ") : "none"
    }
    function B(e) {
        switch (e) {
        case 2:
            return G;
        case 3:
            return J;
        case 4:
            return Q
        }
    }
    function O(e, t) {
        var i = this;
        i.element = e,
        i.options = h({}, O.DEFAULTS, s(t) && t),
        i.isImg = !1,
        i.isBuilt = !1,
        i.isShown = !1,
        i.isViewed = !1,
        i.isFulled = !1,
        i.isPlayed = !1,
        i.wheeling = !1,
        i.playing = !1,
        i.fading = !1,
        i.tooltiping = !1,
        i.transitioning = !1,
        i.action = !1,
        i.target = !1,
        i.timeout = !1,
        i.index = 0,
        i.length = 0,
        i.init()
    }
    var W = e.document
      , H = e.Event
      , j = "viewer"
      , U = j + "-fixed"
      , Z = j + "-open"
      , K = j + "-show"
      , $ = j + "-hide"
      , G = "viewer-hide-xs-down"
      , J = "viewer-hide-sm-down"
      , Q = "viewer-hide-md-down"
      , ee = j + "-fade"
      , te = j + "-in"
      , ie = j + "-move"
      , ne = j + "-active"
      , ae = j + "-invisible"
      , oe = j + "-transition"
      , re = j + "-fullscreen"
      , se = j + "-fullscreen-exit"
      , le = j + "-close"
      , ue = "mousedown touchstart pointerdown MSPointerDown"
      , ce = "mousemove touchmove pointermove MSPointerMove"
      , de = "mouseup touchend touchcancel pointerup pointercancel MSPointerUp MSPointerCancel"
      , me = "wheel mousewheel DOMMouseScroll"
      , fe = "transitionend"
      , he = "load"
      , ve = "keydown"
      , ge = "click"
      , we = "resize"
      , pe = "build"
      , be = "built"
      , ye = "show"
      , xe = "shown"
      , ze = "hide"
      , De = "hidden"
      , Ee = "view"
      , ke = "viewed"
      , Ie = /width|height|left|top|marginLeft|marginTop/
      , Le = /^\s+(.*)\s+$/
      , Te = /\s+/
      , Ye = "undefined" != typeof W.createElement(j).style.transition
      , Fe = Math.min
      , Xe = Math.max
      , Se = Math.abs
      , Ve = Math.sqrt
      , Ne = Math.round
      , Pe = Object.prototype
      , Ae = Pe.toString
      , Ce = Pe.hasOwnProperty
      , _e = Array.prototype.slice;
    O.prototype = {
        constructor: O,
        init: function() {
            var e = this
              , t = e.options
              , i = e.element
              , n = "img" === i.tagName.toLowerCase()
              , a = n ? [i] : S(i, "img")
              , o = a.length
              , r = v(e.ready, e);
            z(i, j) || (D(i, j, e),
            o && (l(t.build) && k(i, pe, t.build, !0),
            L(i, pe) !== !1 && (Ye || (t.transition = !1),
            e.isImg = n,
            e.length = o,
            e.count = 0,
            e.images = a,
            e.body = W.body,
            t.inline ? (k(i, be, function() {
                e.view()
            }, !0),
            f(a, function(e) {
                e.complete ? r() : k(e, he, r, !0)
            })) : k(i, ge, e._start = v(e.start, e)))))
        },
        ready: function() {
            var e = this;
            e.count++,
            e.count === e.length && e.build()
        },
        build: function() {
            var e, t, i, n, a, o, r, s, u = this, c = u.options, d = u.element;
            u.isBuilt || (e = W.createElement("div"),
            e.innerHTML = O.TEMPLATE,
            u.parent = t = d.parentNode,
            u.viewer = i = V(e, "viewer-container")[0],
            u.canvas = V(i, "viewer-canvas")[0],
            u.footer = V(i, "viewer-footer")[0],
            u.title = r = V(i, "viewer-title")[0],
            u.toolbar = a = V(i, "viewer-toolbar")[0],
            u.navbar = o = V(i, "viewer-navbar")[0],
            u.button = n = V(i, "viewer-button")[0],
            u.tooltipBox = V(i, "viewer-tooltip")[0],
            u.player = V(i, "viewer-player")[0],
            u.list = V(i, "viewer-list")[0],
            b(r, c.title ? B(c.title) : $),
            b(a, c.toolbar ? B(c.toolbar) : $),
            b(o, c.navbar ? B(c.navbar) : $),
            x(n, $, !c.button),
            x(a.querySelectorAll("li[class*=zoom]"), ae, !c.zoomable),
            x(a.querySelectorAll("li[class*=flip]"), ae, !c.scalable),
            c.rotatable || (s = a.querySelectorAll("li[class*=rotate]"),
            b(s, ae),
            N(a, s)),
            c.inline ? (b(n, re),
            g(i, {
                zIndex: c.zIndexInline
            }),
            "static" === w(t).position && g(t, {
                position: "relative"
            })) : (b(n, le),
            b(i, U),
            b(i, ee),
            b(i, $),
            g(i, {
                zIndex: c.zIndex
            })),
            t.insertBefore(i, d.nextSibling),
            c.inline && (u.render(),
            u.bind(),
            u.isShown = !0),
            u.isBuilt = !0,
            l(c.built) && k(d, be, c.built, !0),
            L(d, be))
        },
        unbuild: function() {
            var e = this;
            e.isBuilt && (e.isBuilt = !1,
            P(e.viewer))
        },
        bind: function() {
            var t = this
              , i = t.options
              , n = t.element
              , a = t.viewer;
            l(i.view) && k(n, Ee, i.view),
            l(i.viewed) && k(n, ke, i.viewed),
            k(a, ge, t._click = v(t.click, t)),
            k(a, me, t._wheel = v(t.wheel, t)),
            k(t.canvas, ue, t._mousedown = v(t.mousedown, t)),
            k(W, ce, t._mousemove = v(t.mousemove, t)),
            k(W, de, t._mouseup = v(t.mouseup, t)),
            k(W, ve, t._keydown = v(t.keydown, t)),
            k(e, we, t._resize = v(t.resize, t))
        },
        unbind: function() {
            var t = this
              , i = t.options
              , n = t.element
              , a = t.viewer;
            l(i.view) && I(n, Ee, i.view),
            l(i.viewed) && I(n, ke, i.viewed),
            I(a, ge, t._click),
            I(a, me, t._wheel),
            I(t.canvas, ue, t._mousedown),
            I(W, ce, t._mousemove),
            I(W, de, t._mouseup),
            I(W, ve, t._keydown),
            I(e, we, t._resize)
        },
        render: function() {
            var e = this;
            e.initContainer(),
            e.initViewer(),
            e.initList(),
            e.renderViewer()
        },
        initContainer: function() {
            var t = this;
            t.containerData = {
                width: e.innerWidth,
                height: e.innerHeight
            }
        },
        initViewer: function() {
            var e, t = this, i = t.options, n = t.parent;
            i.inline && (t.parentData = e = {
                width: Xe(n.offsetWidth, i.minWidth),
                height: Xe(n.offsetHeight, i.minHeight)
            }),
            (t.isFulled || !e) && (e = t.containerData),
            t.viewerData = h({}, e)
        },
        renderViewer: function() {
            var e = this;
            e.options.inline && !e.isFulled && g(e.viewer, e.viewerData)
        },
        initList: function() {
            var e = this
              , t = e.options
              , i = e.element
              , a = e.list
              , o = [];
            f(e.images, function(a, r) {
                var s = a.src
                  , u = a.alt || R(s)
                  , c = t.url;
                s && (n(c) ? c = a.getAttribute(c) : l(c) && (c = c.call(i, e)),
                o.push('<li><img src="' + s + '" data-action="view" data-index="' + r + '" data-original-url="' + (c || s) + '" alt="' + u + '"></li>'))
            }),
            a.innerHTML = o.join(""),
            f(S(a, "img"), function(t) {
                D(t, "filled", !0),
                k(t, he, v(e.loadImage, e), !0)
            }),
            e.items = S(a, "li"),
            t.transition && k(i, ke, function() {
                b(a, oe)
            }, !0)
        },
        renderList: function(e) {
            var t = this
              , i = e || t.index
              , n = t.items[i].offsetWidth || 30
              , a = n + 1;
            g(t.list, {
                width: a * t.length,
                marginLeft: (t.viewerData.width - n) / 2 - a * i
            })
        },
        resetList: function() {
            var e = this;
            A(e.list),
            y(e.list, oe),
            g({
                marginLeft: 0
            })
        },
        initImage: function(e) {
            var t = this
              , i = t.options
              , n = t.image
              , a = t.viewerData
              , o = t.footer.offsetHeight
              , r = a.width
              , s = Xe(a.height - o, o)
              , u = t.imageData || {};
            q(n, function(n, a) {
                var o, c, d = n / a, m = r, f = s;
                s * d > r ? f = r / d : m = s * d,
                m = Fe(.9 * m, n),
                f = Fe(.9 * f, a),
                c = {
                    naturalWidth: n,
                    naturalHeight: a,
                    aspectRatio: d,
                    ratio: m / n,
                    width: m,
                    height: f,
                    left: (r - m) / 2,
                    top: (s - f) / 2
                },
                o = h({}, c),
                i.rotatable && (c.rotate = u.rotate || 0,
                o.rotate = 0),
                i.scalable && (c.scaleX = u.scaleX || 1,
                c.scaleY = u.scaleY || 1,
                o.scaleX = 1,
                o.scaleY = 1),
                t.imageData = c,
                t.initialImageData = o,
                l(e) && e()
            })
        },
        renderImage: function(e) {
            var t = this
              , i = t.image
              , n = t.imageData
              , a = M(n);
            g(i, {
                width: n.width,
                height: n.height,
                marginLeft: n.left,
                marginTop: n.top,
                WebkitTransform: a,
                msTransform: a,
                transform: a
            }),
            l(e) && (t.transitioning ? k(i, fe, e, !0) : e())
        },
        resetImage: function() {
            var e = this;
            e.image && (P(e.image),
            e.image = null)
        },
        start: function(e) {
            var t = this
              , i = Y(e)
              , n = i.target;
            "img" === n.tagName.toLowerCase() && (t.target = n,
            t.show())
        },
        click: function(e) {
            var t = this
              , i = Y(e)
              , n = i.target
              , a = z(n, "action")
              , o = t.imageData;
            switch (a) {
            case "mix":
                t.isPlayed ? t.stop() : t.options.inline ? t.isFulled ? t.exit() : t.full() : t.hide();
                break;
            case "view":
                t.view(z(n, "static.html.admin.index"));
                break;
            case "zoom-in":
                t.zoom(.1, !0);
                break;
            case "zoom-out":
                t.zoom(-.1, !0);
                break;
            case "one-to-one":
                t.toggle();
                break;
            case "reset":
                t.reset();
                break;
            case "prev":
                t.prev();
                break;
            case "play":
                t.play();
                break;
            case "next":
                t.next();
                break;
            case "rotate-left":
                t.rotate(-90);
                break;
            case "rotate-right":
                t.rotate(90);
                break;
            case "flip-horizontal":
                t.scaleX(-o.scaleX || -1);
                break;
            case "flip-vertical":
                t.scaleY(-o.scaleY || -1);
                break;
            default:
                t.isPlayed && t.stop()
            }
        },
        load: function() {
            var e = this
              , t = e.options
              , i = e.image
              , n = e.viewerData;
            e.timeout && (clearTimeout(e.timeout),
            e.timeout = !1),
            y(i, ae),
            i.style.cssText = "width:0;height:0;margin-left:" + n.width / 2 + "px;margin-top:" + n.height / 2 + "px;max-width:none!important;visibility:visible;",
            e.initImage(function() {
                x(i, oe, t.transition),
                x(i, ie, t.movable),
                e.renderImage(function() {
                    e.isViewed = !0,
                    L(e.element, ke)
                })
            })
        },
        loadImage: function(e) {
            var t = Y(e)
              , i = t.target
              , n = i.parentNode
              , a = n.offsetWidth || 30
              , o = n.offsetHeight || 50
              , r = !!z(i, "filled");
            q(i, function(e, t) {
                var n = e / t
                  , s = a
                  , l = o;
                o * n > a ? r ? s = o * n : l = a / n : r ? l = a / n : s = o * n,
                g(i, {
                    width: s,
                    height: l,
                    marginLeft: (a - s) / 2,
                    marginTop: (o - l) / 2
                })
            })
        },
        resize: function() {
            var e = this;
            e.initContainer(),
            e.initViewer(),
            e.renderViewer(),
            e.renderList(),
            e.isViewed && e.initImage(function() {
                e.renderImage()
            }),
            e.isPlayed && f(S(e.player, "img"), function(t) {
                k(t, he, v(e.loadImage, e), !0),
                L(t, he)
            })
        },
        wheel: function(e) {
            var t = this
              , i = Y(e)
              , n = Number(t.options.zoomRatio) || .1
              , a = 1;
            t.isViewed && (T(i),
            t.wheeling || (t.wheeling = !0,
            setTimeout(function() {
                t.wheeling = !1
            }, 50),
            i.deltaY ? a = i.deltaY > 0 ? 1 : -1 : i.wheelDelta ? a = -i.wheelDelta / 120 : i.detail && (a = i.detail > 0 ? 1 : -1),
            t.zoom(-a * n, !0, i)))
        },
        keydown: function(e) {
            var t = this
              , i = Y(e)
              , n = t.options
              , a = i.keyCode || i.which || i.charCode;
            if (t.isFulled && n.keyboard)
                switch (a) {
                case 27:
                    t.isPlayed ? t.stop() : n.inline ? t.isFulled && t.exit() : t.hide();
                    break;
                case 32:
                    t.isPlayed && t.stop();
                    break;
                case 37:
                    t.prev();
                    break;
                case 38:
                    T(i),
                    t.zoom(n.zoomRatio, !0);
                    break;
                case 39:
                    t.next();
                    break;
                case 40:
                    T(i),
                    t.zoom(-n.zoomRatio, !0);
                    break;
                case 48:
                case 49:
                    (i.ctrlKey || i.shiftKey) && (T(i),
                    t.toggle())
                }
        },
        mousedown: function(e) {
            var t, i, n = this, a = n.options, o = Y(e), r = a.movable ? "move" : !1, s = o.touches;
            if (n.isViewed) {
                if (s) {
                    if (t = s.length,
                    t > 1) {
                        if (!a.zoomable || 2 !== t)
                            return;
                        i = s[1],
                        n.startX2 = i.pageX,
                        n.startY2 = i.pageY,
                        r = "zoom"
                    } else
                        n.isSwitchable() && (r = "switch");
                    i = s[0]
                }
                r && (T(o),
                n.action = r,
                n.startX = i ? i.pageX : o.pageX,
                n.startY = i ? i.pageY : o.pageY)
            }
        },
        mousemove: function(e) {
            var t, i, n = this, a = n.options, o = Y(e), r = n.action, s = n.image, l = o.touches;
            if (n.isViewed) {
                if (l) {
                    if (t = l.length,
                    t > 1) {
                        if (!a.zoomable || 2 !== t)
                            return;
                        i = l[1],
                        n.endX2 = i.pageX,
                        n.endY2 = i.pageY
                    }
                    i = l[0]
                }
                r && (T(o),
                "move" === r && a.transition && p(s, oe) && y(s, oe),
                n.endX = i ? i.pageX : o.pageX,
                n.endY = i ? i.pageY : o.pageY,
                n.change(o))
            }
        },
        mouseup: function(e) {
            var t = this
              , i = Y(e)
              , n = t.action;
            n && (T(i),
            "move" === n && t.options.transition && b(t.image, oe),
            t.action = !1)
        },
        show: function() {
            var e, t = this, i = t.options, n = t.element;
            return i.inline || t.transitioning ? t : (t.isBuilt || t.build(),
            e = t.viewer,
            l(i.show) && k(n, ye, i.show, !0),
            L(n, ye) === !1 ? t : (b(t.body, Z),
            y(e, $),
            k(n, xe, function() {
                t.view(t.target ? d(t.target, c(t.images)) : t.index),
                t.target = !1
            }, !0),
            i.transition ? (t.transitioning = !0,
            b(e, oe),
            _(e),
            k(e, fe, v(t.shown, t), !0),
            b(e, te)) : (b(e, te),
            t.shown()),
            t))
        },
        hide: function() {
            var e = this
              , t = e.options
              , i = e.element
              , n = e.viewer;
            return t.inline || e.transitioning || !e.isShown ? e : (l(t.hide) && k(i, ze, t.hide, !0),
            L(i, ze) === !1 ? e : (e.isViewed && t.transition ? (e.transitioning = !0,
            k(e.image, fe, function() {
                k(n, fe, v(e.hidden, e), !0),
                y(n, te)
            }, !0),
            e.zoomTo(0, !1, !1, !0)) : (y(n, te),
            e.hidden()),
            e))
        },
        view: function(e) {
            var t, i, n, a, o, r = this, s = r.element, l = r.title, u = r.canvas;
            return e = Number(e) || 0,
            !r.isShown || r.isPlayed || 0 > e || e >= r.length || r.isViewed && e === r.index ? r : L(s, Ee) === !1 ? r : (i = r.items[e],
            n = S(i, "img")[0],
            a = z(n, "originalUrl"),
            o = n.getAttribute("alt"),
            t = W.createElement("img"),
            t.src = a,
            t.alt = o,
            r.image = t,
            r.isViewed && y(r.items[r.index], ne),
            b(i, ne),
            r.isViewed = !1,
            r.index = e,
            r.imageData = null,
            b(u, ae),
            A(u),
            N(u, t),
            r.renderList(),
            A(l),
            k(s, ke, function() {
                var e = r.imageData
                  , t = e.naturalWidth
                  , i = e.naturalHeight;
                C(l, o + " (" + t + " × " + i + ")")
            }, !0),
            t.complete ? r.load() : (k(t, he, v(r.load, r), !0),
            r.timeout && clearTimeout(r.timeout),
            r.timeout = setTimeout(function() {
                y(t, ae),
                r.timeout = !1
            }, 1e3)),
            r)
        },
        prev: function() {
            var e = this;
            return e.view(Xe(e.index - 1, 0)),
            e
        },
        next: function() {
            var e = this;
            return e.view(Fe(e.index + 1, e.length - 1)),
            e
        },
        move: function(e, t) {
            var i = this
              , n = i.imageData;
            return i.moveTo(o(e) ? e : n.left + Number(e), o(t) ? t : n.top + Number(t)),
            i
        },
        moveTo: function(e, t) {
            var i = this
              , n = i.imageData
              , r = !1;
            return o(t) && (t = e),
            e = Number(e),
            t = Number(t),
            i.isViewed && !i.isPlayed && i.options.movable && (a(e) && (n.left = e,
            r = !0),
            a(t) && (n.top = t,
            r = !0),
            r && i.renderImage()),
            i
        },
        zoom: function(e, t, i) {
            var n = this
              , a = n.imageData;
            return e = Number(e),
            e = 0 > e ? 1 / (1 - e) : 1 + e,
            n.zoomTo(a.width * e / a.naturalWidth, t, i),
            n
        },
        zoomTo: function(e, t, i, n) {
            var o, r, s, l, u = this, c = u.options, d = .01, m = 100, f = u.imageData;
            return e = Xe(0, e),
            a(e) && u.isViewed && !u.isPlayed && (n || c.zoomable) && (n || (d = Xe(d, c.minZoomRatio),
            m = Fe(m, c.maxZoomRatio),
            e = Fe(Xe(e, d), m)),
            e > .95 && 1.05 > e && (e = 1),
            o = f.naturalWidth * e,
            r = f.naturalHeight * e,
            i ? (s = F(u.viewer),
            l = i.touches ? X(i.touches) : {
                pageX: i.pageX,
                pageY: i.pageY
            },
            f.left -= (o - f.width) * ((l.pageX - s.left - f.left) / f.width),
            f.top -= (r - f.height) * ((l.pageY - s.top - f.top) / f.height)) : (f.left -= (o - f.width) / 2,
            f.top -= (r - f.height) / 2),
            f.width = o,
            f.height = r,
            f.ratio = e,
            u.renderImage(),
            t && u.tooltip()),
            u
        },
        rotate: function(e) {
            var t = this;
            return t.rotateTo((t.imageData.rotate || 0) + Number(e)),
            t
        },
        rotateTo: function(e) {
            var t = this
              , i = t.imageData;
            return e = Number(e),
            a(e) && t.isViewed && !t.isPlayed && t.options.rotatable && (i.rotate = e,
            t.renderImage()),
            t
        },
        scale: function(e, t) {
            var i = this
              , n = i.imageData
              , r = !1;
            return o(t) && (t = e),
            e = Number(e),
            t = Number(t),
            i.isViewed && !i.isPlayed && i.options.scalable && (a(e) && (n.scaleX = e,
            r = !0),
            a(t) && (n.scaleY = t,
            r = !0),
            r && i.renderImage()),
            i
        },
        scaleX: function(e) {
            var t = this;
            return t.scale(e, t.imageData.scaleY),
            t
        },
        scaleY: function(e) {
            var t = this;
            return t.scale(t.imageData.scaleX, e),
            t
        },
        play: function() {
            var e, t = this, i = t.options, n = t.player, o = v(t.loadImage, t), r = [], s = 0, l = 0;
            return !t.isShown || t.isPlayed ? t : (i.fullscreen && t.requestFullscreen(),
            t.isPlayed = !0,
            b(n, K),
            f(t.items, function(e, t) {
                var a = S(e, "img")[0]
                  , u = W.createElement("img");
                u.src = z(a, "originalUrl"),
                u.alt = a.getAttribute("alt"),
                s++,
                b(u, ee),
                x(u, oe, i.transition),
                p(e, ne) && (b(u, te),
                l = t),
                r.push(u),
                k(u, he, o, !0),
                N(n, u)
            }),
            a(i.interval) && i.interval > 0 && (e = function() {
                t.playing = setTimeout(function() {
                    y(r[l], te),
                    l++,
                    l = s > l ? l : 0,
                    b(r[l], te),
                    e()
                }, i.interval)
            }
            ,
            s > 1 && e()),
            t)
        },
        stop: function() {
            var e = this
              , t = e.player;
            return e.isPlayed ? (e.options.fullscreen && e.exitFullscreen(),
            e.isPlayed = !1,
            clearTimeout(e.playing),
            y(t, K),
            A(t),
            e) : e
        },
        full: function() {
            var e = this
              , t = e.options
              , i = e.viewer
              , n = e.image
              , a = e.list;
            return !e.isShown || e.isPlayed || e.isFulled || !t.inline ? e : (e.isFulled = !0,
            b(e.body, Z),
            b(e.button, se),
            t.transition && (y(n, oe),
            y(a, oe)),
            b(i, U),
            i.setAttribute("style", ""),
            g(i, {
                zIndex: t.zIndex
            }),
            e.initContainer(),
            e.viewerData = h({}, e.containerData),
            e.renderList(),
            e.initImage(function() {
                e.renderImage(function() {
                    t.transition && setTimeout(function() {
                        b(n, oe),
                        b(a, oe)
                    }, 0)
                })
            }),
            e)
        },
        exit: function() {
            var e = this
              , t = e.options
              , i = e.viewer
              , n = e.image
              , a = e.list;
            return e.isFulled ? (e.isFulled = !1,
            y(e.body, Z),
            y(e.button, se),
            t.transition && (y(n, oe),
            y(a, oe)),
            y(i, U),
            g(i, {
                zIndex: t.zIndexInline
            }),
            e.viewerData = h({}, e.parentData),
            e.renderViewer(),
            e.renderList(),
            e.initImage(function() {
                e.renderImage(function() {
                    t.transition && setTimeout(function() {
                        b(n, oe),
                        b(a, oe)
                    }, 0)
                })
            }),
            e) : e
        },
        tooltip: function() {
            var e = this
              , t = e.options
              , i = e.tooltipBox
              , n = e.imageData;
            return e.isViewed && !e.isPlayed && t.tooltip ? (C(i, Ne(100 * n.ratio) + "%"),
            e.tooltiping ? clearTimeout(e.tooltiping) : t.transition ? (e.fading && L(i, fe),
            b(i, K),
            b(i, ee),
            b(i, oe),
            _(i),
            b(i, te)) : b(i, K),
            e.tooltiping = setTimeout(function() {
                t.transition ? (k(i, fe, function() {
                    y(i, K),
                    y(i, ee),
                    y(i, oe),
                    e.fading = !1
                }, !0),
                y(i, te),
                e.fading = !0) : y(i, K),
                e.tooltiping = !1
            }, 1e3),
            e) : e
        },
        toggle: function() {
            var e = this;
            return 1 === e.imageData.ratio ? e.zoomTo(e.initialImageData.ratio, !0) : e.zoomTo(1, !0),
            e
        },
        reset: function() {
            var e = this;
            return e.isViewed && !e.isPlayed && (e.imageData = h({}, e.initialImageData),
            e.renderImage()),
            e
        },
        update: function() {
            var e, t = this, i = [];
            return t.isImg && !t.element.parentNode ? t.destroy() : (t.length = t.images.length,
            t.isBuilt && (f(t.items, function(e, n) {
                var a = S(e, "img")[0]
                  , o = t.images[n];
                o ? o.src !== a.src && i.push(n) : i.push(n)
            }),
            g(t.list, {
                width: "auto"
            }),
            t.initList(),
            t.isShown && (t.length ? t.isViewed && (e = d(t.index, i),
            e >= 0 ? (t.isViewed = !1,
            t.view(Xe(t.index - (e + 1), 0))) : b(t.items[t.index], ne)) : (t.image = null,
            t.isViewed = !1,
            t.index = 0,
            t.imageData = null,
            A(t.canvas),
            A(t.title)))),
            t)
        },
        destroy: function() {
            var e = this
              , t = e.element;
            return e.options.inline ? e.unbind() : (e.isShown && e.unbind(),
            I(t, ge, e._start)),
            e.unbuild(),
            E(t, j),
            e
        },
        shown: function() {
            var e = this
              , t = e.options
              , i = e.element;
            e.transitioning = !1,
            e.isFulled = !0,
            e.isShown = !0,
            e.isVisible = !0,
            e.render(),
            e.bind(),
            l(t.shown) && k(i, xe, t.shown, !0),
            L(i, xe)
        },
        hidden: function() {
            var e = this
              , t = e.options
              , i = e.element;
            e.transitioning = !1,
            e.isViewed = !1,
            e.isFulled = !1,
            e.isShown = !1,
            e.isVisible = !1,
            e.unbind(),
            y(e.body, Z),
            b(e.viewer, $),
            e.resetList(),
            e.resetImage(),
            l(t.hidden) && k(i, De, t.hidden, !0),
            L(i, De)
        },
        requestFullscreen: function() {
            var e = this
              , t = W.documentElement;
            !e.isFulled || W.fullscreenElement || W.mozFullScreenElement || W.webkitFullscreenElement || W.msFullscreenElement || (t.requestFullscreen ? t.requestFullscreen() : t.msRequestFullscreen ? t.msRequestFullscreen() : t.mozRequestFullScreen ? t.mozRequestFullScreen() : t.webkitRequestFullscreen && t.webkitRequestFullscreen(Element.ALLOW_KEYBOARD_INPUT))
        },
        exitFullscreen: function() {
            var e = this;
            e.isFulled && (W.exitFullscreen ? W.exitFullscreen() : W.msExitFullscreen ? W.msExitFullscreen() : W.mozCancelFullScreen ? W.mozCancelFullScreen() : W.webkitExitFullscreen && W.webkitExitFullscreen())
        },
        change: function(e) {
            var t = this
              , i = t.endX - t.startX
              , n = t.endY - t.startY;
            switch (t.action) {
            case "move":
                t.move(i, n);
                break;
            case "zoom":
                t.zoom(function(e, t, i, n) {
                    var a = Ve(e * e + t * t)
                      , o = Ve(i * i + n * n);
                    return (o - a) / a
                }(Se(t.startX - t.startX2), Se(t.startY - t.startY2), Se(t.endX - t.endX2), Se(t.endY - t.endY2)), !1, e),
                t.startX2 = t.endX2,
                t.startY2 = t.endY2;
                break;
            case "switch":
                t.action = "switched",
                Se(i) > Se(n) && (i > 1 ? t.prev() : -1 > i && t.next())
            }
            t.startX = t.endX,
            t.startY = t.endY
        },
        isSwitchable: function() {
            var e = this
              , t = e.imageData
              , i = e.viewerData;
            return t.left >= 0 && t.top >= 0 && t.width <= i.width && t.height <= i.height
        }
    },
    O.DEFAULTS = {
        inline: !1,
        button: !0,
        navbar: !0,
        title: !0,
        toolbar: !0,
        tooltip: !0,
        movable: !0,
        zoomable: !0,
        rotatable: !0,
        scalable: !0,
        transition: !0,
        fullscreen: !0,
        keyboard: !0,
        interval: 5e3,
        minWidth: 200,
        minHeight: 100,
        zoomRatio: .1,
        minZoomRatio: .01,
        maxZoomRatio: 100,
        zIndex: 9999999,
        zIndexInline: 0,
        url: "src",
        build: null,
        built: null,
        show: null,
        shown: null,
        hide: null,
        hidden: null,
        view: null,
        viewed: null
    },
    O.TEMPLATE = '<div class="viewer-container"><div class="viewer-canvas"></div><div class="viewer-footer"><div class="viewer-title"></div><ul class="viewer-toolbar"><li class="viewer-zoom-in" data-action="zoom-in"></li><li class="viewer-zoom-out" data-action="zoom-out"></li><li class="viewer-one-to-one" data-action="one-to-one"></li><li class="viewer-reset" data-action="reset"></li><li class="viewer-prev" data-action="prev"></li><li class="viewer-play" data-action="play"></li><li class="viewer-next" data-action="next"></li><li class="viewer-rotate-left" data-action="rotate-left"></li><li class="viewer-rotate-right" data-action="rotate-right"></li><li class="viewer-flip-horizontal" data-action="flip-horizontal"></li><li class="viewer-flip-vertical" data-action="flip-vertical"></li></ul><div class="viewer-navbar"><ul class="viewer-list"></ul></div></div><div class="viewer-tooltip"></div><div class="viewer-button" data-action="mix"></div><div class="viewer-player"></div></div>';
    var Re = e.Viewer;
    return O.noConflict = function() {
        return e.Viewer = Re,
        O
    }
    ,
    O.setDefaults = function(e) {
        h(O.DEFAULTS, e)
    }
    ,
    "function" == typeof define && define.amd && define("viewer", [], function() {
        return O
    }),
    t || (e.Viewer = O),
    O
});
