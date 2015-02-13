(function(f, n) {
    function e(a) {
        var b = Ia[a] = {};
        c.each(a.split(X),
        function(a, c) {
            b[c] = !0
        });
        return b
    }
    function g(a, b, d) {
        if (d === n && 1 === a.nodeType) if (d = "data-" + b.replace(xb, "-$1").toLowerCase(), d = a.getAttribute(d), "string" === typeof d) {
            try {
                d = "true" === d ? !0 : "false" === d ? !1 : "null" === d ? null: +d + "" === d ? +d: yb.test(d) ? c.parseJSON(d) : d
            } catch(l) {}
            c.data(a, b, d)
        } else d = n;
        return d
    }
    function h(a) {
        for (var b in a) if (! ("data" === b && c.isEmptyObject(a[b])) && "toJSON" !== b) return ! 1;
        return ! 0
    }
    function p() {
        return ! 1
    }
    function r() {
        return ! 0
    }
    function s(a) {
        return ! a || !a.parentNode || 11 === a.parentNode.nodeType
    }
    function v(a, b) {
        do a = a[b];
        while (a && 1 !== a.nodeType);
        return a
    }
    function G(a, b, d) {
        b = b || 0;
        if (c.isFunction(b)) return c.grep(a,
        function(a, c) {
            return !! b.call(a, c, a) === d
        });
        if (b.nodeType) return c.grep(a,
        function(a, c) {
            return a === b === d
        });
        if ("string" === typeof b) {
            var l = c.grep(a,
            function(a) {
                return 1 === a.nodeType
            });
            if (zb.test(b)) return c.filter(b, l, !d);
            b = c.filter(b, l)
        }
        return c.grep(a,
        function(a, l) {
            return 0 <= c.inArray(a, b) === d
        })
    }
    function y(a) {
        var b = Ja.split("|");
        a = a.createDocumentFragment();
        if (a.createElement) for (; b.length;) a.createElement(b.pop());
        return a
    }
    function N(a, b) {
        if (1 === b.nodeType && c.hasData(a)) {
            var d, l, q;
            l = c._data(a);
            var k = c._data(b, l),
            e = l.events;
            if (e) for (d in delete k.handle, k.events = {},
            e) {
                l = 0;
                for (q = e[d].length; l < q; l++) c.event.add(b, d, e[d][l])
            }
            k.data && (k.data = c.extend({},
            k.data))
        }
    }
    function F(a, b) {
        var d;
        1 === b.nodeType && (b.clearAttributes && b.clearAttributes(), b.mergeAttributes && b.mergeAttributes(a), d = b.nodeName.toLowerCase(), "object" === d ? (b.parentNode && (b.outerHTML = a.outerHTML), c.support.html5Clone && (a.innerHTML && !c.trim(b.innerHTML)) && (b.innerHTML = a.innerHTML)) : "input" === d && Ka.test(a.type) ? (b.defaultChecked = b.checked = a.checked, b.value !== a.value && (b.value = a.value)) : "option" === d ? b.selected = a.defaultSelected: "input" === d || "textarea" === d ? b.defaultValue = a.defaultValue: "script" === d && b.text !== a.text && (b.text = a.text), b.removeAttribute(c.expando))
    }
    function D(a) {
        return "undefined" !== typeof a.getElementsByTagName ? a.getElementsByTagName("*") : "undefined" !== typeof a.querySelectorAll ? a.querySelectorAll("*") : []
    }
    function K(a) {
        Ka.test(a.type) && (a.defaultChecked = a.checked)
    }
    function J(a, b) {
        if (b in a) return b;
        for (var d = b.charAt(0).toUpperCase() + b.slice(1), c = b, q = La.length; q--;) if (b = La[q] + d, b in a) return b;
        return c
    }
    function Q(a, b) {
        a = b || a;
        return "none" === c.css(a, "display") || !c.contains(a.ownerDocument, a)
    }
    function ma(a, b) {
        for (var d, l, q = [], k = 0, e = a.length; k < e; k++) d = a[k],
        d.style && (q[k] = c._data(d, "olddisplay"), b ? (!q[k] && "none" === d.style.display && (d.style.display = ""), "" === d.style.display && Q(d) && (q[k] = c._data(d, "olddisplay", ea(d.nodeName)))) : (l = z(d, "display"), !q[k] && "none" !== l && c._data(d, "olddisplay", l)));
        for (k = 0; k < e; k++) if (d = a[k], d.style && (!b || "none" === d.style.display || "" === d.style.display)) d.style.display = b ? q[k] || "": "none";
        return a
    }
    function I(a, b, d) {
        return (a = Ab.exec(b)) ? Math.max(0, a[1] - (d || 0)) + (a[2] || "px") : b
    }
    function na(a, b, d, l) {
        b = d === (l ? "border": "content") ? 4 : "width" === b ? 1 : 0;
        for (var q = 0; 4 > b; b += 2)"margin" === d && (q += c.css(a, d + O[b], !0)),
        l ? ("content" === d && (q -= parseFloat(z(a, "padding" + O[b])) || 0), "margin" !== d && (q -= parseFloat(z(a, "border" + O[b] + "Width")) || 0)) : (q += parseFloat(z(a, "padding" + O[b])) || 0, "padding" !== d && (q += parseFloat(z(a, "border" + O[b] + "Width")) || 0));
        return q
    }
    function M(a, b, d) {
        var l = "width" === b ? a.offsetWidth: a.offsetHeight,
        q = !0,
        k = c.support.boxSizing && "border-box" === c.css(a, "boxSizing");
        if (0 >= l || null == l) {
            l = z(a, b);
            if (0 > l || null == l) l = a.style[b];
            if (oa.test(l)) return l;
            q = k && (c.support.boxSizingReliable || l === a.style[b]);
            l = parseFloat(l) || 0
        }
        return l + na(a, b, d || (k ? "border": "content"), q) + "px"
    }
    function ea(a) {
        if (ya[a]) return ya[a];
        var b = c("<" + a + ">").appendTo(u.body),
        d = b.css("display");
        b.remove();
        if ("none" === d || "" === d) {
            aa = u.body.appendChild(aa || c.extend(u.createElement("iframe"), {
                frameBorder: 0,
                width: 0,
                height: 0
            }));
            if (!da || !aa.createElement) da = (aa.contentWindow || aa.contentDocument).document,
            da.write("<!doctype html><html><body>"),
            da.close();
            b = da.body.appendChild(da.createElement(a));
            d = z(b, "display");
            u.body.removeChild(aa)
        }
        return ya[a] = d
    }
    function ia(a, b, d, l) {
        var q;
        if (c.isArray(b)) c.each(b,
        function(b, c) {
            d || Bb.test(a) ? l(a, c) : ia(a + "[" + ("object" === typeof c ? b: "") + "]", c, d, l)
        });
        else if (!d && "object" === c.type(b)) for (q in b) ia(a + "[" + q + "]", b[q], d, l);
        else l(a, b)
    }
    function Ma(a) {
        return function(b, d) {
            "string" !== typeof b && (d = b, b = "*");
            var l, q, k = b.toLowerCase().split(X),
            e = 0,
            m = k.length;
            if (c.isFunction(d)) for (; e < m; e++) l = k[e],
            (q = /^\+/.test(l)) && (l = l.substr(1) || "*"),
            l = a[l] = a[l] || [],
            l[q ? "unshift": "push"](d)
        }
    }
    function Y(a, b, d, c, q, k) {
        q = q || b.dataTypes[0];
        k = k || {};
        k[q] = !0;
        var e;
        q = a[q];
        for (var m = 0,
        f = q ? q.length: 0, g = a === za; m < f && (g || !e); m++) e = q[m](b, d, c),
        "string" === typeof e && (!g || k[e] ? e = n: (b.dataTypes.unshift(e), e = Y(a, b, d, c, e, k)));
        if ((g || !e) && !k["*"]) e = Y(a, b, d, c, "*", k);
        return e
    }
    function Na(a, b) {
        var d, l, q = c.ajaxSettings.flatOptions || {};
        for (d in b) b[d] !== n && ((q[d] ? a: l || (l = {}))[d] = b[d]);
        l && c.extend(!0, a, l)
    }
    function Oa() {
        try {
            return new f.XMLHttpRequest
        } catch(a) {}
    }
    function Pa() {
        setTimeout(function() {
            fa = n
        },
        0);
        return fa = c.now()
    }
    function Db(a, b) {
        c.each(b,
        function(b, c) {
            for (var q = (ja[b] || []).concat(ja["*"]), k = 0, e = q.length; k < e && !q[k].call(a, b, c); k++);
        })
    }
    function Qa(a, b, d) {
        var l = 0,
        q = pa.length,
        k = c.Deferred().always(function() {
            delete e.elem
        }),
        e = function() {
            for (var b = fa || Pa(), b = Math.max(0, m.startTime + m.duration - b), d = 1 - (b / m.duration || 0), c = 0, l = m.tweens.length; c < l; c++) m.tweens[c].run(d);
            k.notifyWith(a, [m, d, b]);
            if (1 > d && l) return b;
            k.resolveWith(a, [m]);
            return ! 1
        },
        m = k.promise({
            elem: a,
            props: c.extend({},
            b),
            opts: c.extend(!0, {
                specialEasing: {}
            },
            d),
            originalProperties: b,
            originalOptions: d,
            startTime: fa || Pa(),
            duration: d.duration,
            tweens: [],
            createTween: function(b, d, l) {
                b = c.Tween(a, m.opts, b, d, m.opts.specialEasing[b] || m.opts.easing);
                m.tweens.push(b);
                return b
            },
            stop: function(b) {
                for (var d = 0,
                c = b ? m.tweens.length: 0; d < c; d++) m.tweens[d].run(1);
                b ? k.resolveWith(a, [m, b]) : k.rejectWith(a, [m, b]);
                return this
            }
        });
        d = m.props;
        for (Eb(d, m.opts.specialEasing); l < q; l++) if (b = pa[l].call(m, a, d, m.opts)) return b;
        Db(m, d);
        c.isFunction(m.opts.start) && m.opts.start.call(a, m);
        c.fx.timer(c.extend(e, {
            anim: m,
            queue: m.opts.queue,
            elem: a
        }));
        return m.progress(m.opts.progress).done(m.opts.done, m.opts.complete).fail(m.opts.fail).always(m.opts.always)
    }
    function Eb(a, b) {
        var d, l, q, k, e;
        for (d in a) if (l = c.camelCase(d), q = b[l], k = a[d], c.isArray(k) && (q = k[1], k = a[d] = k[0]), d !== l && (a[l] = k, delete a[d]), (e = c.cssHooks[l]) && "expand" in e) for (d in k = e.expand(k), delete a[l], k) d in a || (a[d] = k[d], b[d] = q);
        else b[l] = q
    }
    function E(a, b, d, c, q) {
        return new E.prototype.init(a, b, d, c, q)
    }
    function qa(a, b) {
        var d, c = {
            height: a
        },
        q = 0;
        for (b = b ? 1 : 0; 4 > q; q += 2 - b) d = O[q],
        c["margin" + d] = c["padding" + d] = a;
        b && (c.opacity = c.width = a);
        return c
    }
    function Ra(a) {
        return c.isWindow(a) ? a: 9 === a.nodeType ? a.defaultView || a.parentWindow: !1
    }
    var Sa, ra, u = f.document,
    Fb = f.location,
    Gb = f.navigator,
    Hb = f.jQuery,
    Ib = f.$,
    Ta = Array.prototype.push,
    S = Array.prototype.slice,
    Ua = Array.prototype.indexOf,
    Jb = Object.prototype.toString,
    Aa = Object.prototype.hasOwnProperty,
    Ba = String.prototype.trim,
    c = function(a, b) {
        return new c.fn.init(a, b, Sa)
    },
    sa = /[\-+]?(?:\d*\.|)\d+(?:[eE][\-+]?\d+|)/.source,
    Kb = /\S/,
    X = /\s+/,
    Lb = /^[\s\uFEFF\xA0]+|[\s\uFEFF\xA0]+$/g,
    Mb = /^(?:[^#<]*(<[\w\W]+>)[^>]*$|#([\w\-]*)$)/,
    Va = /^<(\w+)\s*\/?>(?:<\/\1>|)$/,
    Nb = /^[\],:{}\s]*$/,
    Ob = /(?:^|:|,)(?:\s*\[)+/g,
    Pb = /\\(?:["\\\/bfnrt]|u[\da-fA-F]{4})/g,
    Qb = /"[^"\\\r\n]*"|true|false|null|-?(?:\d\d*\.|)\d+(?:[eE][\-+]?\d+|)/g,
    Rb = /^-ms-/,
    Sb = /-([\da-z])/gi,
    Tb = function(a, b) {
        return (b + "").toUpperCase()
    },
    ta = function() {
        u.addEventListener ? (u.removeEventListener("DOMContentLoaded", ta, !1), c.ready()) : "complete" === u.readyState && (u.detachEvent("onreadystatechange", ta), c.ready())
    },
    Wa = {};
    c.fn = c.prototype = {
        constructor: c,
        init: function(a, b, d) {
            var l;
            if (!a) return this;
            if (a.nodeType) return this.context = this[0] = a,
            this.length = 1,
            this;
            if ("string" === typeof a) {
                if ((l = "<" === a.charAt(0) && ">" === a.charAt(a.length - 1) && 3 <= a.length ? [null, a, null] : Mb.exec(a)) && (l[1] || !b)) {
                    if (l[1]) return a = (b = b instanceof c ? b[0] : b) && b.nodeType ? b.ownerDocument || b: u,
                    a = c.parseHTML(l[1], a, !0),
                    Va.test(l[1]) && c.isPlainObject(b) && this.attr.call(a, b, !0),
                    c.merge(this, a);
                    if ((b = u.getElementById(l[2])) && b.parentNode) {
                        if (b.id !== l[2]) return d.find(a);
                        this.length = 1;
                        this[0] = b
                    }
                    this.context = u;
                    this.selector = a;
                    return this
                }
                return ! b || b.jquery ? (b || d).find(a) : this.constructor(b).find(a)
            }
            if (c.isFunction(a)) return d.ready(a);
            a.selector !== n && (this.selector = a.selector, this.context = a.context);
            return c.makeArray(a, this)
        },
        selector: "",
        jquery: "1.8.3",
        length: 0,
        size: function() {
            return this.length
        },
        toArray: function() {
            return S.call(this)
        },
        get: function(a) {
            return null == a ? this.toArray() : 0 > a ? this[this.length + a] : this[a]
        },
        pushStack: function(a, b, d) {
            a = c.merge(this.constructor(), a);
            a.prevObject = this;
            a.context = this.context;
            "find" === b ? a.selector = this.selector + (this.selector ? " ": "") + d: b && (a.selector = this.selector + "." + b + "(" + d + ")");
            return a
        },
        each: function(a, b) {
            return c.each(this, a, b)
        },
        ready: function(a) {
            c.ready.promise().done(a);
            return this
        },
        eq: function(a) {
            a = +a;
            return - 1 === a ? this.slice(a) : this.slice(a, a + 1)
        },
        first: function() {
            return this.eq(0)
        },
        last: function() {
            return this.eq( - 1)
        },
        slice: function() {
            return this.pushStack(S.apply(this, arguments), "slice", S.call(arguments).join(","))
        },
        map: function(a) {
            return this.pushStack(c.map(this,
            function(b, d) {
                return a.call(b, d, b)
            }))
        },
        end: function() {
            return this.prevObject || this.constructor(null)
        },
        push: Ta,
        sort: [].sort,
        splice: [].splice
    };
    c.fn.init.prototype = c.fn;
    c.extend = c.fn.extend = function() {
        var a, b, d, l, q, k = arguments[0] || {},
        e = 1,
        m = arguments.length,
        f = !1;
        "boolean" === typeof k && (f = k, k = arguments[1] || {},
        e = 2);
        "object" !== typeof k && !c.isFunction(k) && (k = {});
        m === e && (k = this, --e);
        for (; e < m; e++) if (null != (a = arguments[e])) for (b in a) d = k[b],
        l = a[b],
        k !== l && (f && l && (c.isPlainObject(l) || (q = c.isArray(l))) ? (q ? (q = !1, d = d && c.isArray(d) ? d: []) : d = d && c.isPlainObject(d) ? d: {},
        k[b] = c.extend(f, d, l)) : l !== n && (k[b] = l));
        return k
    };
    c.extend({
        noConflict: function(a) {
            f.$ === c && (f.$ = Ib);
            a && f.jQuery === c && (f.jQuery = Hb);
            return c
        },
        isReady: !1,
        readyWait: 1,
        holdReady: function(a) {
            a ? c.readyWait++:c.ready(!0)
        },
        ready: function(a) {
            if (! (!0 === a ? --c.readyWait: c.isReady)) {
                if (!u.body) return setTimeout(c.ready, 1);
                c.isReady = !0; ! 0 !== a && 0 < --c.readyWait || (ra.resolveWith(u, [c]), c.fn.trigger && c(u).trigger("ready").off("ready"))
            }
        },
        isFunction: function(a) {
            return "function" === c.type(a)
        },
        isArray: Array.isArray ||
        function(a) {
            return "array" === c.type(a)
        },
        isWindow: function(a) {
            return null != a && a == a.window
        },
        isNumeric: function(a) {
            return ! isNaN(parseFloat(a)) && isFinite(a)
        },
        type: function(a) {
            return null == a ? String(a) : Wa[Jb.call(a)] || "object"
        },
        isPlainObject: function(a) {
            if (!a || "object" !== c.type(a) || a.nodeType || c.isWindow(a)) return ! 1;
            try {
                if (a.constructor && !Aa.call(a, "constructor") && !Aa.call(a.constructor.prototype, "isPrototypeOf")) return ! 1
            } catch(b) {
                return ! 1
            }
            for (var d in a);
            return d === n || Aa.call(a, d)
        },
        isEmptyObject: function(a) {
            for (var b in a) return ! 1;
            return ! 0
        },
        error: function(a) {
            throw Error(a);
        },
        parseHTML: function(a, b, d) {
            var l;
            if (!a || "string" !== typeof a) return null;
            "boolean" === typeof b && (d = b, b = 0);
            b = b || u;
            if (l = Va.exec(a)) return [b.createElement(l[1])];
            l = c.buildFragment([a], b, d ? null: []);
            return c.merge([], (l.cacheable ? c.clone(l.fragment) : l.fragment).childNodes)
        },
        parseJSON: function(a) {
            if (!a || "string" !== typeof a) return null;
            a = c.trim(a);
            if (f.JSON && f.JSON.parse) return f.JSON.parse(a);
            if (Nb.test(a.replace(Pb, "@").replace(Qb, "]").replace(Ob, ""))) return (new Function("return " + a))();
            c.error("Invalid JSON: " + a)
        },
        parseXML: function(a) {
            var b, d;
            if (!a || "string" !== typeof a) return null;
            try {
                f.DOMParser ? (d = new DOMParser, b = d.parseFromString(a, "text/xml")) : (b = new ActiveXObject("Microsoft.XMLDOM"), b.async = "false", b.loadXML(a))
            } catch(l) {
                b = n
            } (!b || !b.documentElement || b.getElementsByTagName("parsererror").length) && c.error("Invalid XML: " + a);
            return b
        },
        noop: function() {},
        globalEval: function(a) {
            a && Kb.test(a) && (f.execScript ||
            function(a) {
                f.eval.call(f, a)
            })(a)
        },
        camelCase: function(a) {
            return a.replace(Rb, "ms-").replace(Sb, Tb)
        },
        nodeName: function(a, b) {
            return a.nodeName && a.nodeName.toLowerCase() === b.toLowerCase()
        },
        each: function(a, b, d) {
            var l, q = 0,
            k = a.length,
            e = k === n || c.isFunction(a);
            if (d) if (e) for (l in a) {
                if (!1 === b.apply(a[l], d)) break
            } else for (; q < k && !1 !== b.apply(a[q++], d););
            else if (e) for (l in a) {
                if (!1 === b.call(a[l], l, a[l])) break
            } else for (; q < k && !1 !== b.call(a[q], q, a[q++]););
            return a
        },
        trim: Ba && !Ba.call("\ufeff\u00a0") ?
        function(a) {
            return null == a ? "": Ba.call(a)
        }: function(a) {
            return null == a ? "": (a + "").replace(Lb, "")
        },
        makeArray: function(a, b) {
            var d, l = b || [];
            null != a && (d = c.type(a), null == a.length || "string" === d || "function" === d || "regexp" === d || c.isWindow(a) ? Ta.call(l, a) : c.merge(l, a));
            return l
        },
        inArray: function(a, b, d) {
            var c;
            if (b) {
                if (Ua) return Ua.call(b, a, d);
                c = b.length;
                for (d = d ? 0 > d ? Math.max(0, c + d) : d: 0; d < c; d++) if (d in b && b[d] === a) return d
            }
            return - 1
        },
        merge: function(a, b) {
            var d = b.length,
            c = a.length,
            q = 0;
            if ("number" === typeof d) for (; q < d; q++) a[c++] = b[q];
            else for (; b[q] !== n;) a[c++] = b[q++];
            a.length = c;
            return a
        },
        grep: function(a, b, d) {
            var c, q = [],
            k = 0,
            e = a.length;
            for (d = !!d; k < e; k++) c = !!b(a[k], k),
            d !== c && q.push(a[k]);
            return q
        },
        map: function(a, b, d) {
            var l, q, k = [],
            e = 0,
            m = a.length;
            if (a instanceof c || m !== n && "number" === typeof m && (0 < m && a[0] && a[m - 1] || 0 === m || c.isArray(a))) for (; e < m; e++) l = b(a[e], e, d),
            null != l && (k[k.length] = l);
            else for (q in a) l = b(a[q], q, d),
            null != l && (k[k.length] = l);
            return k.concat.apply([], k)
        },
        guid: 1,
        proxy: function(a, b) {
            var d, l;
            "string" === typeof b && (d = a[b], b = a, a = d);
            if (!c.isFunction(a)) return n;
            l = S.call(arguments, 2);
            d = function() {
                return a.apply(b, l.concat(S.call(arguments)))
            };
            d.guid = a.guid = a.guid || c.guid++;
            return d
        },
        access: function(a, b, d, l, q, e, A) {
            var m, f = null == d,
            g = 0,
            h = a.length;
            if (d && "object" === typeof d) {
                for (g in d) c.access(a, b, g, d[g], 1, e, l);
                q = 1
            } else if (l !== n) {
                m = A === n && c.isFunction(l);
                f && (m ? (m = b, b = function(a, b, d) {
                    return m.call(c(a), d)
                }) : (b.call(a, l), b = null));
                if (b) for (; g < h; g++) b(a[g], d, m ? l.call(a[g], g, b(a[g], d)) : l, A);
                q = 1
            }
            return q ? a: f ? b.call(a) : h ? b(a[0], d) : e
        },
        now: function() {
            return (new Date).getTime()
        }
    });
    c.ready.promise = function(a) {
        if (!ra) if (ra = c.Deferred(), "complete" === u.readyState) setTimeout(c.ready, 1);
        else if (u.addEventListener) u.addEventListener("DOMContentLoaded", ta, !1),
        f.addEventListener("load", c.ready, !1);
        else {
            u.attachEvent("onreadystatechange", ta);
            f.attachEvent("onload", c.ready);
            var b = !1;
            try {
                b = null == f.frameElement && u.documentElement
            } catch(d) {}
            b && b.doScroll &&
            function q() {
                if (!c.isReady) {
                    try {
                        b.doScroll("left")
                    } catch(a) {
                        return setTimeout(q, 50)
                    }
                    c.ready()
                }
            } ()
        }
        return ra.promise(a)
    };
    c.each("Boolean Number String Function Array Date RegExp Object".split(" "),
    function(a, b) {
        Wa["[object " + b + "]"] = b.toLowerCase()
    });
    Sa = c(u);
    var Ia = {};
    c.Callbacks = function(a) {
        a = "string" === typeof a ? Ia[a] || e(a) : c.extend({},
        a);
        var b, d, l, q, k, A, m = [],
        f = !a.once && [],
        g = function(c) {
            b = a.memory && c;
            d = !0;
            A = q || 0;
            q = 0;
            k = m.length;
            for (l = !0; m && A < k; A++) if (!1 === m[A].apply(c[0], c[1]) && a.stopOnFalse) {
                b = !1;
                break
            }
            l = !1;
            m && (f ? f.length && g(f.shift()) : b ? m = [] : h.disable())
        },
        h = {
            add: function() {
                if (m) {
                    var d = m.length; (function Ub(b) {
                        c.each(b,
                        function(b, d) {
                            var l = c.type(d);
                            "function" === l ? (!a.unique || !h.has(d)) && m.push(d) : d && (d.length && "string" !== l) && Ub(d)
                        })
                    })(arguments);
                    l ? k = m.length: b && (q = d, g(b))
                }
                return this
            },
            remove: function() {
                m && c.each(arguments,
                function(a, b) {
                    for (var d; - 1 < (d = c.inArray(b, m, d));) m.splice(d, 1),
                    l && (d <= k && k--, d <= A && A--)
                });
                return this
            },
            has: function(a) {
                return - 1 < c.inArray(a, m)
            },
            empty: function() {
                m = [];
                return this
            },
            disable: function() {
                m = f = b = n;
                return this
            },
            disabled: function() {
                return ! m
            },
            lock: function() {
                f = n;
                b || h.disable();
                return this
            },
            locked: function() {
                return ! f
            },
            fireWith: function(a, b) {
                b = b || [];
                b = [a, b.slice ? b.slice() : b];
                if (m && (!d || f)) l ? f.push(b) : g(b);
                return this
            },
            fire: function() {
                h.fireWith(this, arguments);
                return this
            },
            fired: function() {
                return !! d
            }
        };
        return h
    };
    c.extend({
        Deferred: function(a) {
            var b = [["resolve", "done", c.Callbacks("once memory"), "resolved"], ["reject", "fail", c.Callbacks("once memory"), "rejected"], ["notify", "progress", c.Callbacks("memory")]],
            d = "pending",
            l = {
                state: function() {
                    return d
                },
                always: function() {
                    q.done(arguments).fail(arguments);
                    return this
                },
                then: function() {
                    var a = arguments;
                    return c.Deferred(function(d) {
                        c.each(b,
                        function(b, l) {
                            var e = l[0],
                            f = a[b];
                            q[l[1]](c.isFunction(f) ?
                            function() {
                                var a = f.apply(this, arguments);
                                if (a && c.isFunction(a.promise)) a.promise().done(d.resolve).fail(d.reject).progress(d.notify);
                                else d[e + "With"](this === q ? d: this, [a])
                            }: d[e])
                        });
                        a = null
                    }).promise()
                },
                promise: function(a) {
                    return null != a ? c.extend(a, l) : l
                }
            },
            q = {};
            l.pipe = l.then;
            c.each(b,
            function(a, c) {
                var e = c[2],
                f = c[3];
                l[c[1]] = e.add;
                f && e.add(function() {
                    d = f
                },
                b[a ^ 1][2].disable, b[2][2].lock);
                q[c[0]] = e.fire;
                q[c[0] + "With"] = e.fireWith
            });
            l.promise(q);
            a && a.call(q, q);
            return q
        },
        when: function(a) {
            var b = 0,
            d = S.call(arguments),
            l = d.length,
            q = 1 !== l || a && c.isFunction(a.promise) ? l: 0,
            e = 1 === q ? a: c.Deferred(),
            f = function(a, b, d) {
                return function(c) {
                    b[a] = this;
                    d[a] = 1 < arguments.length ? S.call(arguments) : c;
                    d === m ? e.notifyWith(b, d) : --q || e.resolveWith(b, d)
                }
            },
            m,
            g,
            h;
            if (1 < l) {
                m = Array(l);
                g = Array(l);
                for (h = Array(l); b < l; b++) d[b] && c.isFunction(d[b].promise) ? d[b].promise().done(f(b, h, d)).fail(e.reject).progress(f(b, g, m)) : --q
            }
            q || e.resolveWith(h, d);
            return e.promise()
        }
    });
    c.support = function() {
        var a, b, d, l, q, e, A, m = u.createElement("div");
        m.setAttribute("className", "t");
        m.innerHTML = "  <link/><table></table><a href='/a'>a</a><input type='checkbox'/>";
        b = m.getElementsByTagName("*");
        d = m.getElementsByTagName("a")[0];
        if (!b || !d || !b.length) return {};
        l = u.createElement("select");
        q = l.appendChild(u.createElement("option"));
        b = m.getElementsByTagName("input")[0];
        d.style.cssText = "top:1px;float:left;opacity:.5";
        a = {
            leadingWhitespace: 3 === m.firstChild.nodeType,
            tbody: !m.getElementsByTagName("tbody").length,
            htmlSerialize: !!m.getElementsByTagName("link").length,
            style: /top/.test(d.getAttribute("style")),
            hrefNormalized: "/a" === d.getAttribute("href"),
            opacity: /^0.5/.test(d.style.opacity),
            cssFloat: !!d.style.cssFloat,
            checkOn: "on" === b.value,
            optSelected: q.selected,
            getSetAttribute: "t" !== m.className,
            enctype: !!u.createElement("form").enctype,
            html5Clone: "<:nav></:nav>" !== u.createElement("nav").cloneNode(!0).outerHTML,
            boxModel: "CSS1Compat" === u.compatMode,
            submitBubbles: !0,
            changeBubbles: !0,
            focusinBubbles: !1,
            deleteExpando: !0,
            noCloneEvent: !0,
            inlineBlockNeedsLayout: !1,
            shrinkWrapBlocks: !1,
            reliableMarginRight: !0,
            boxSizingReliable: !0,
            pixelPosition: !1
        };
        b.checked = !0;
        a.noCloneChecked = b.cloneNode(!0).checked;
        l.disabled = !0;
        a.optDisabled = !q.disabled;
        try {
            delete m.test
        } catch(g) {
            a.deleteExpando = !1
        } ! m.addEventListener && (m.attachEvent && m.fireEvent) && (m.attachEvent("onclick", d = function() {
            a.noCloneEvent = !1
        }), m.cloneNode(!0).fireEvent("onclick"), m.detachEvent("onclick", d));
        b = u.createElement("input");
        b.value = "t";
        b.setAttribute("type", "radio");
        a.radioValue = "t" === b.value;
        b.setAttribute("checked", "checked");
        b.setAttribute("name", "t");
        m.appendChild(b);
        d = u.createDocumentFragment();
        d.appendChild(m.lastChild);
        a.checkClone = d.cloneNode(!0).cloneNode(!0).lastChild.checked;
        a.appendChecked = b.checked;
        d.removeChild(b);
        d.appendChild(m);
        if (m.attachEvent) for (e in {
            submit: !0,
            change: !0,
            focusin: !0
        }) b = "on" + e,
        A = b in m,
        A || (m.setAttribute(b, "return;"), A = "function" === typeof m[b]),
        a[e + "Bubbles"] = A;
        c(function() {
            var b, d, c, l = u.getElementsByTagName("body")[0];
            l && (b = u.createElement("div"), b.style.cssText = "visibility:hidden;border:0;width:0;height:0;position:static;top:0;margin-top:1px", l.insertBefore(b, l.firstChild), d = u.createElement("div"), b.appendChild(d), d.innerHTML = "<table><tr><td></td><td>t</td></tr></table>", c = d.getElementsByTagName("td"), c[0].style.cssText = "padding:0;margin:0;border:0;display:none", A = 0 === c[0].offsetHeight, c[0].style.display = "", c[1].style.display = "none", a.reliableHiddenOffsets = A && 0 === c[0].offsetHeight, d.innerHTML = "", d.style.cssText = "box-sizing:border-box;-moz-box-sizing:border-box;-webkit-box-sizing:border-box;padding:1px;border:1px;display:block;width:4px;margin-top:1%;position:absolute;top:1%;", a.boxSizing = 4 === d.offsetWidth, a.doesNotIncludeMarginInBodyOffset = 1 !== l.offsetTop, f.getComputedStyle && (a.pixelPosition = "1%" !== (f.getComputedStyle(d, null) || {}).top, a.boxSizingReliable = "4px" === (f.getComputedStyle(d, null) || {
                width: "4px"
            }).width, c = u.createElement("div"), c.style.cssText = d.style.cssText = "padding:0;margin:0;border:0;display:block;overflow:hidden;", c.style.marginRight = c.style.width = "0", d.style.width = "1px", d.appendChild(c), a.reliableMarginRight = !parseFloat((f.getComputedStyle(c, null) || {}).marginRight)), "undefined" !== typeof d.style.zoom && (d.innerHTML = "", d.style.cssText = "padding:0;margin:0;border:0;display:block;overflow:hidden;width:1px;padding:1px;display:inline;zoom:1", a.inlineBlockNeedsLayout = 3 === d.offsetWidth, d.style.display = "block", d.style.overflow = "visible", d.innerHTML = "<div></div>", d.firstChild.style.width = "5px", a.shrinkWrapBlocks = 3 !== d.offsetWidth, b.style.zoom = 1), l.removeChild(b))
        });
        d.removeChild(m);
        b = d = l = q = b = d = m = null;
        return a
    } ();
    var yb = /(?:\{[\s\S]*\}|\[[\s\S]*\])$/,
    xb = /([A-Z])/g;
    c.extend({
        cache: {},
        deletedIds: [],
        uuid: 0,
        expando: "jQuery" + (c.fn.jquery + Math.random()).replace(/\D/g, ""),
        noData: {
            embed: !0,
            object: "clsid:D27CDB6E-AE6D-11cf-96B8-444553540000",
            applet: !0
        },
        hasData: function(a) {
            a = a.nodeType ? c.cache[a[c.expando]] : a[c.expando];
            return !! a && !h(a)
        },
        data: function(a, b, d, l) {
            if (c.acceptData(a)) {
                var q = c.expando,
                e = "string" === typeof b,
                f = a.nodeType,
                m = f ? c.cache: a,
                g = f ? a[q] : a[q] && q;
                if (g && m[g] && (l || m[g].data) || !(e && d === n)) {
                    g || (f ? a[q] = g = c.deletedIds.pop() || c.guid++:g = q);
                    m[g] || (m[g] = {},
                    f || (m[g].toJSON = c.noop));
                    if ("object" === typeof b || "function" === typeof b) l ? m[g] = c.extend(m[g], b) : m[g].data = c.extend(m[g].data, b);
                    a = m[g];
                    l || (a.data || (a.data = {}), a = a.data);
                    d !== n && (a[c.camelCase(b)] = d);
                    e ? (d = a[b], null == d && (d = a[c.camelCase(b)])) : d = a;
                    return d
                }
            }
        },
        removeData: function(a, b, d) {
            if (c.acceptData(a)) {
                var l, q, e, f = a.nodeType,
                m = f ? c.cache: a,
                g = f ? a[c.expando] : c.expando;
                if (m[g]) {
                    if (b && (l = d ? m[g] : m[g].data)) {
                        c.isArray(b) || (b in l ? b = [b] : (b = c.camelCase(b), b = b in l ? [b] : b.split(" ")));
                        q = 0;
                        for (e = b.length; q < e; q++) delete l[b[q]];
                        if (! (d ? h: c.isEmptyObject)(l)) return
                    }
                    if (!d && (delete m[g].data, !h(m[g]))) return;
                    f ? c.cleanData([a], !0) : c.support.deleteExpando || m != m.window ? delete m[g] : m[g] = null
                }
            }
        },
        _data: function(a, b, d) {
            return c.data(a, b, d, !0)
        },
        acceptData: function(a) {
            var b = a.nodeName && c.noData[a.nodeName.toLowerCase()];
            return ! b || !0 !== b && a.getAttribute("classid") === b
        }
    });
    c.fn.extend({
        data: function(a, b) {
            var d, l, q, e, f, m = this[0],
            h = 0,
            p = null;
            if (a === n) {
                if (this.length && (p = c.data(m), 1 === m.nodeType && !c._data(m, "parsedAttrs"))) {
                    q = m.attributes;
                    for (f = q.length; h < f; h++) e = q[h].name,
                    e.indexOf("data-") || (e = c.camelCase(e.substring(5)), g(m, e, p[e]));
                    c._data(m, "parsedAttrs", !0)
                }
                return p
            }
            if ("object" === typeof a) return this.each(function() {
                c.data(this, a)
            });
            d = a.split(".", 2);
            d[1] = d[1] ? "." + d[1] : "";
            l = d[1] + "!";
            return c.access(this,
            function(b) {
                if (b === n) return p = this.triggerHandler("getData" + l, [d[0]]),
                p === n && m && (p = c.data(m, a), p = g(m, a, p)),
                p === n && d[1] ? this.data(d[0]) : p;
                d[1] = b;
                this.each(function() {
                    var e = c(this);
                    e.triggerHandler("setData" + l, d);
                    c.data(this, a, b);
                    e.triggerHandler("changeData" + l, d)
                })
            },
            null, b, 1 < arguments.length, null, !1)
        },
        removeData: function(a) {
            return this.each(function() {
                c.removeData(this, a)
            })
        }
    });
    c.extend({
        queue: function(a, b, d) {
            var l;
            if (a) return b = (b || "fx") + "queue",
            l = c._data(a, b),
            d && (!l || c.isArray(d) ? l = c._data(a, b, c.makeArray(d)) : l.push(d)),
            l || []
        },
        dequeue: function(a, b) {
            b = b || "fx";
            var d = c.queue(a, b),
            l = d.length,
            e = d.shift(),
            k = c._queueHooks(a, b),
            f = function() {
                c.dequeue(a, b)
            };
            "inprogress" === e && (e = d.shift(), l--);
            e && ("fx" === b && d.unshift("inprogress"), delete k.stop, e.call(a, f, k)); ! l && k && k.empty.fire()
        },
        _queueHooks: function(a, b) {
            var d = b + "queueHooks";
            return c._data(a, d) || c._data(a, d, {
                empty: c.Callbacks("once memory").add(function() {
                    c.removeData(a, b + "queue", !0);
                    c.removeData(a, d, !0)
                })
            })
        }
    });
    c.fn.extend({
        queue: function(a, b) {
            var d = 2;
            "string" !== typeof a && (b = a, a = "fx", d--);
            return arguments.length < d ? c.queue(this[0], a) : b === n ? this: this.each(function() {
                var d = c.queue(this, a, b);
                c._queueHooks(this, a);
                "fx" === a && "inprogress" !== d[0] && c.dequeue(this, a)
            })
        },
        dequeue: function(a) {
            return this.each(function() {
                c.dequeue(this, a)
            })
        },
        delay: function(a, b) {
            a = c.fx ? c.fx.speeds[a] || a: a;
            return this.queue(b || "fx",
            function(b, c) {
                var e = setTimeout(b, a);
                c.stop = function() {
                    clearTimeout(e)
                }
            })
        },
        clearQueue: function(a) {
            return this.queue(a || "fx", [])
        },
        promise: function(a, b) {
            var d, l = 1,
            e = c.Deferred(),
            k = this,
            f = this.length,
            m = function() {--l || e.resolveWith(k, [k])
            };
            "string" !== typeof a && (b = a, a = n);
            for (a = a || "fx"; f--;) if ((d = c._data(k[f], a + "queueHooks")) && d.empty) l++,
            d.empty.add(m);
            m();
            return e.promise(b)
        }
    });
    var U, Xa, Ya, Za = /[\t\r\n]/g,
    Vb = /\r/g,
    Wb = /^(?:button|input)$/i,
    Xb = /^(?:button|input|object|select|textarea)$/i,
    Yb = /^a(?:rea|)$/i,
    $a = /^(?:autofocus|autoplay|async|checked|controls|defer|disabled|hidden|loop|multiple|open|readonly|required|scoped|selected)$/i,
    ab = c.support.getSetAttribute;
    c.fn.extend({
        attr: function(a, b) {
            return c.access(this, c.attr, a, b, 1 < arguments.length)
        },
        removeAttr: function(a) {
            return this.each(function() {
                c.removeAttr(this, a)
            })
        },
        prop: function(a, b) {
            return c.access(this, c.prop, a, b, 1 < arguments.length)
        },
        removeProp: function(a) {
            a = c.propFix[a] || a;
            return this.each(function() {
                try {
                    this[a] = n,
                    delete this[a]
                } catch(b) {}
            })
        },
        addClass: function(a) {
            var b, d, l, e, k, f, m;
            if (c.isFunction(a)) return this.each(function(b) {
                c(this).addClass(a.call(this, b, this.className))
            });
            if (a && "string" === typeof a) {
                b = a.split(X);
                d = 0;
                for (l = this.length; d < l; d++) if (e = this[d], 1 === e.nodeType) if (!e.className && 1 === b.length) e.className = a;
                else {
                    k = " " + e.className + " ";
                    f = 0;
                    for (m = b.length; f < m; f++) 0 > k.indexOf(" " + b[f] + " ") && (k += b[f] + " ");
                    e.className = c.trim(k)
                }
            }
            return this
        },
        removeClass: function(a) {
            var b, d, l, e, k, f, m;
            if (c.isFunction(a)) return this.each(function(b) {
                c(this).removeClass(a.call(this, b, this.className))
            });
            if (a && "string" === typeof a || a === n) {
                b = (a || "").split(X);
                f = 0;
                for (m = this.length; f < m; f++) if (l = this[f], 1 === l.nodeType && l.className) {
                    d = (" " + l.className + " ").replace(Za, " ");
                    e = 0;
                    for (k = b.length; e < k; e++) for (; 0 <= d.indexOf(" " + b[e] + " ");) d = d.replace(" " + b[e] + " ", " ");
                    l.className = a ? c.trim(d) : ""
                }
            }
            return this
        },
        toggleClass: function(a, b) {
            var d = typeof a,
            l = "boolean" === typeof b;
            return c.isFunction(a) ? this.each(function(d) {
                c(this).toggleClass(a.call(this, d, this.className, b), b)
            }) : this.each(function() {
                if ("string" === d) for (var e, k = 0,
                f = c(this), m = b, g = a.split(X); e = g[k++];) m = l ? m: !f.hasClass(e),
                f[m ? "addClass": "removeClass"](e);
                else if ("undefined" === d || "boolean" === d) this.className && c._data(this, "__className__", this.className),
                this.className = this.className || !1 === a ? "": c._data(this, "__className__") || ""
            })
        },
        hasClass: function(a) {
            a = " " + a + " ";
            for (var b = 0,
            d = this.length; b < d; b++) if (1 === this[b].nodeType && 0 <= (" " + this[b].className + " ").replace(Za, " ").indexOf(a)) return ! 0;
            return ! 1
        },
        val: function(a) {
            var b, d, l, e = this[0];
            if (arguments.length) return l = c.isFunction(a),
            this.each(function(d) {
                var e = c(this);
                if (1 === this.nodeType && (d = l ? a.call(this, d, e.val()) : a, null == d ? d = "": "number" === typeof d ? d += "": c.isArray(d) && (d = c.map(d,
                function(a) {
                    return null == a ? "": a + ""
                })), b = c.valHooks[this.type] || c.valHooks[this.nodeName.toLowerCase()], !b || !("set" in b) || b.set(this, d, "value") === n)) this.value = d
            });
            if (e) {
                if ((b = c.valHooks[e.type] || c.valHooks[e.nodeName.toLowerCase()]) && "get" in b && (d = b.get(e, "value")) !== n) return d;
                d = e.value;
                return "string" === typeof d ? d.replace(Vb, "") : null == d ? "": d
            }
        }
    });
    c.extend({
        valHooks: {
            option: {
                get: function(a) {
                    var b = a.attributes.value;
                    return ! b || b.specified ? a.value: a.text
                }
            },
            select: {
                get: function(a) {
                    for (var b, d = a.options,
                    l = a.selectedIndex,
                    e = (a = "select-one" === a.type || 0 > l) ? null: [], k = a ? l + 1 : d.length, f = 0 > l ? k: a ? l: 0; f < k; f++) if (b = d[f], (b.selected || f === l) && (c.support.optDisabled ? !b.disabled: null === b.getAttribute("disabled")) && (!b.parentNode.disabled || !c.nodeName(b.parentNode, "optgroup"))) {
                        b = c(b).val();
                        if (a) return b;
                        e.push(b)
                    }
                    return e
                },
                set: function(a, b) {
                    var d = c.makeArray(b);
                    c(a).find("option").each(function() {
                        this.selected = 0 <= c.inArray(c(this).val(), d)
                    });
                    d.length || (a.selectedIndex = -1);
                    return d
                }
            }
        },
        attrFn: {},
        attr: function(a, b, d, l) {
            var e, k, f = a.nodeType;
            if (a && !(3 === f || 8 === f || 2 === f)) {
                if (l && c.isFunction(c.fn[b])) return c(a)[b](d);
                if ("undefined" === typeof a.getAttribute) return c.prop(a, b, d);
                if (l = 1 !== f || !c.isXMLDoc(a)) b = b.toLowerCase(),
                k = c.attrHooks[b] || ($a.test(b) ? Xa: U);
                if (d !== n) if (null === d) c.removeAttr(a, b);
                else {
                    if (k && "set" in k && l && (e = k.set(a, d, b)) !== n) return e;
                    a.setAttribute(b, d + "");
                    return d
                } else {
                    if (k && "get" in k && l && null !== (e = k.get(a, b))) return e;
                    e = a.getAttribute(b);
                    return null === e ? n: e
                }
            }
        },
        removeAttr: function(a, b) {
            var d, l, e, k, f = 0;
            if (b && 1 === a.nodeType) for (l = b.split(X); f < l.length; f++) if (e = l[f]) d = c.propFix[e] || e,
            (k = $a.test(e)) || c.attr(a, e, ""),
            a.removeAttribute(ab ? e: d),
            k && d in a && (a[d] = !1)
        },
        attrHooks: {
            type: {
                set: function(a, b) {
                    if (Wb.test(a.nodeName) && a.parentNode) c.error("type property can't be changed");
                    else if (!c.support.radioValue && "radio" === b && c.nodeName(a, "input")) {
                        var d = a.value;
                        a.setAttribute("type", b);
                        d && (a.value = d);
                        return b
                    }
                }
            },
            value: {
                get: function(a, b) {
                    return U && c.nodeName(a, "button") ? U.get(a, b) : b in a ? a.value: null
                },
                set: function(a, b, d) {
                    if (U && c.nodeName(a, "button")) return U.set(a, b, d);
                    a.value = b
                }
            }
        },
        propFix: {
            tabindex: "tabIndex",
            readonly: "readOnly",
            "for": "htmlFor",
            "class": "className",
            maxlength: "maxLength",
            cellspacing: "cellSpacing",
            cellpadding: "cellPadding",
            rowspan: "rowSpan",
            colspan: "colSpan",
            usemap: "useMap",
            frameborder: "frameBorder",
            contenteditable: "contentEditable"
        },
        prop: function(a, b, d) {
            var l, e, k;
            k = a.nodeType;
            if (a && !(3 === k || 8 === k || 2 === k)) {
                if (k = 1 !== k || !c.isXMLDoc(a)) b = c.propFix[b] || b,
                e = c.propHooks[b];
                return d !== n ? e && "set" in e && (l = e.set(a, d, b)) !== n ? l: a[b] = d: e && "get" in e && null !== (l = e.get(a, b)) ? l: a[b]
            }
        },
        propHooks: {
            tabIndex: {
                get: function(a) {
                    var b = a.getAttributeNode("tabindex");
                    return b && b.specified ? parseInt(b.value, 10) : Xb.test(a.nodeName) || Yb.test(a.nodeName) && a.href ? 0 : n
                }
            }
        }
    });
    Xa = {
        get: function(a, b) {
            var d, l = c.prop(a, b);
            return ! 0 === l || "boolean" !== typeof l && (d = a.getAttributeNode(b)) && !1 !== d.nodeValue ? b.toLowerCase() : n
        },
        set: function(a, b, d) { ! 1 === b ? c.removeAttr(a, d) : (b = c.propFix[d] || d, b in a && (a[b] = !0), a.setAttribute(d, d.toLowerCase()));
            return d
        }
    };
    ab || (Ya = {
        name: !0,
        id: !0,
        coords: !0
    },
    U = c.valHooks.button = {
        get: function(a, b) {
            var d;
            return (d = a.getAttributeNode(b)) && (Ya[b] ? "" !== d.value: d.specified) ? d.value: n
        },
        set: function(a, b, d) {
            var c = a.getAttributeNode(d);
            c || (c = u.createAttribute(d), a.setAttributeNode(c));
            return c.value = b + ""
        }
    },
    c.each(["width", "height"],
    function(a, b) {
        c.attrHooks[b] = c.extend(c.attrHooks[b], {
            set: function(a, c) {
                if ("" === c) return a.setAttribute(b, "auto"),
                c
            }
        })
    }), c.attrHooks.contenteditable = {
        get: U.get,
        set: function(a, b, d) {
            "" === b && (b = "false");
            U.set(a, b, d)
        }
    });
    c.support.hrefNormalized || c.each(["href", "src", "width", "height"],
    function(a, b) {
        c.attrHooks[b] = c.extend(c.attrHooks[b], {
            get: function(a) {
                a = a.getAttribute(b, 2);
                return null === a ? n: a
            }
        })
    });
    c.support.style || (c.attrHooks.style = {
        get: function(a) {
            return a.style.cssText.toLowerCase() || n
        },
        set: function(a, b) {
            return a.style.cssText = b + ""
        }
    });
    c.support.optSelected || (c.propHooks.selected = c.extend(c.propHooks.selected, {
        get: function(a) {
            if (a = a.parentNode) a.selectedIndex,
            a.parentNode && a.parentNode.selectedIndex;
            return null
        }
    }));
    c.support.enctype || (c.propFix.enctype = "encoding");
    c.support.checkOn || c.each(["radio", "checkbox"],
    function() {
        c.valHooks[this] = {
            get: function(a) {
                return null === a.getAttribute("value") ? "on": a.value
            }
        }
    });
    c.each(["radio", "checkbox"],
    function() {
        c.valHooks[this] = c.extend(c.valHooks[this], {
            set: function(a, b) {
                if (c.isArray(b)) return a.checked = 0 <= c.inArray(c(a).val(), b)
            }
        })
    });
    var Ca = /^(?:textarea|input|select)$/i,
    bb = /^([^\.]*|)(?:\.(.+)|)$/,
    Zb = /(?:^|\s)hover(\.\S+|)\b/,
    $b = /^key/,
    ac = /^(?:mouse|contextmenu)|click/,
    cb = /^(?:focusinfocus|focusoutblur)$/,
    db = function(a) {
        return c.event.special.hover ? a: a.replace(Zb, "mouseenter$1 mouseleave$1")
    };
    c.event = {
        add: function(a, b, d, l, e) {
            var k, f, m, g, h, p, t, r, s;
            if (! (3 === a.nodeType || 8 === a.nodeType || !b || !d || !(k = c._data(a)))) {
                d.handler && (t = d, d = t.handler, e = t.selector);
                d.guid || (d.guid = c.guid++);
                m = k.events;
                m || (k.events = m = {});
                f = k.handle;
                f || (k.handle = f = function(a) {
                    return "undefined" !== typeof c && (!a || c.event.triggered !== a.type) ? c.event.dispatch.apply(f.elem, arguments) : n
                },
                f.elem = a);
                b = c.trim(db(b)).split(" ");
                for (k = 0; k < b.length; k++) {
                    g = bb.exec(b[k]) || [];
                    h = g[1];
                    p = (g[2] || "").split(".").sort();
                    s = c.event.special[h] || {};
                    h = (e ? s.delegateType: s.bindType) || h;
                    s = c.event.special[h] || {};
                    g = c.extend({
                        type: h,
                        origType: g[1],
                        data: l,
                        handler: d,
                        guid: d.guid,
                        selector: e,
                        needsContext: e && c.expr.match.needsContext.test(e),
                        namespace: p.join(".")
                    },
                    t);
                    r = m[h];
                    if (!r && (r = m[h] = [], r.delegateCount = 0, !s.setup || !1 === s.setup.call(a, l, p, f))) a.addEventListener ? a.addEventListener(h, f, !1) : a.attachEvent && a.attachEvent("on" + h, f);
                    s.add && (s.add.call(a, g), g.handler.guid || (g.handler.guid = d.guid));
                    e ? r.splice(r.delegateCount++, 0, g) : r.push(g);
                    c.event.global[h] = !0
                }
                a = null
            }
        },
        global: {},
        remove: function(a, b, d, l, e) {
            var k, f, m, g, h, n, p, r, s, u, y = c.hasData(a) && c._data(a);
            if (y && (p = y.events)) {
                b = c.trim(db(b || "")).split(" ");
                for (k = 0; k < b.length; k++) if (f = bb.exec(b[k]) || [], m = g = f[1], f = f[2], m) {
                    r = c.event.special[m] || {};
                    m = (l ? r.delegateType: r.bindType) || m;
                    s = p[m] || [];
                    h = s.length;
                    f = f ? RegExp("(^|\\.)" + f.split(".").sort().join("\\.(?:.*\\.|)") + "(\\.|$)") : null;
                    for (n = 0; n < s.length; n++) if (u = s[n], (e || g === u.origType) && (!d || d.guid === u.guid) && (!f || f.test(u.namespace)) && (!l || l === u.selector || "**" === l && u.selector)) s.splice(n--, 1),
                    u.selector && s.delegateCount--,
                    r.remove && r.remove.call(a, u);
                    0 === s.length && h !== s.length && ((!r.teardown || !1 === r.teardown.call(a, f, y.handle)) && c.removeEvent(a, m, y.handle), delete p[m])
                } else for (m in p) c.event.remove(a, m + b[k], d, l, !0);
                c.isEmptyObject(p) && (delete y.handle, c.removeData(a, "events", !0))
            }
        },
        customEvent: {
            getData: !0,
            setData: !0,
            changeData: !0
        },
        trigger: function(a, b, d, l) {
            if (!d || !(3 === d.nodeType || 8 === d.nodeType)) {
                var e, k, g, m, h, p, r = a.type || a;
                g = [];
                if (!cb.test(r + c.event.triggered) && (0 <= r.indexOf("!") && (r = r.slice(0, -1), e = !0), 0 <= r.indexOf(".") && (g = r.split("."), r = g.shift(), g.sort()), d && !c.event.customEvent[r] || c.event.global[r])) if (a = "object" === typeof a ? a[c.expando] ? a: new c.Event(r, a) : new c.Event(r), a.type = r, a.isTrigger = !0, a.exclusive = e, a.namespace = g.join("."), a.namespace_re = a.namespace ? RegExp("(^|\\.)" + g.join("\\.(?:.*\\.|)") + "(\\.|$)") : null, g = 0 > r.indexOf(":") ? "on" + r: "", d) {
                    if (a.result = n, a.target || (a.target = d), b = null != b ? c.makeArray(b) : [], b.unshift(a), m = c.event.special[r] || {},
                    !(m.trigger && !1 === m.trigger.apply(d, b))) {
                        p = [[d, m.bindType || r]];
                        if (!l && !m.noBubble && !c.isWindow(d)) {
                            h = m.delegateType || r;
                            e = cb.test(h + r) ? d: d.parentNode;
                            for (k = d; e; e = e.parentNode) p.push([e, h]),
                            k = e;
                            if (k === (d.ownerDocument || u)) p.push([k.defaultView || k.parentWindow || f, h])
                        }
                        for (k = 0; k < p.length && !a.isPropagationStopped(); k++) e = p[k][0],
                        a.type = p[k][1],
                        (h = (c._data(e, "events") || {})[a.type] && c._data(e, "handle")) && h.apply(e, b),
                        (h = g && e[g]) && (c.acceptData(e) && h.apply && !1 === h.apply(e, b)) && a.preventDefault();
                        a.type = r;
                        if (!l && !a.isDefaultPrevented() && (!m._default || !1 === m._default.apply(d.ownerDocument, b)) && !("click" === r && c.nodeName(d, "a")) && c.acceptData(d)) if (g && d[r] && ("focus" !== r && "blur" !== r || 0 !== a.target.offsetWidth) && !c.isWindow(d))(k = d[g]) && (d[g] = null),
                        c.event.triggered = r,
                        d[r](),
                        c.event.triggered = n,
                        k && (d[g] = k);
                        return a.result
                    }
                } else for (k in d = c.cache, d) d[k].events && d[k].events[r] && c.event.trigger(a, b, d[k].handle.elem, !0)
            }
        },
        dispatch: function(a) {
            a = c.event.fix(a || f.event);
            var b, d, e, q, k, g, m = (c._data(this, "events") || {})[a.type] || [],
            h = m.delegateCount,
            p = S.call(arguments),
            r = !a.exclusive && !a.namespace,
            t = c.event.special[a.type] || {},
            s = [];
            p[0] = a;
            a.delegateTarget = this;
            if (! (t.preDispatch && !1 === t.preDispatch.call(this, a))) {
                if (h && !(a.button && "click" === a.type)) for (d = a.target; d != this; d = d.parentNode || this) if (!0 !== d.disabled || "click" !== a.type) {
                    q = {};
                    k = [];
                    for (b = 0; b < h; b++) e = m[b],
                    g = e.selector,
                    q[g] === n && (q[g] = e.needsContext ? 0 <= c(g, this).index(d) : c.find(g, this, null, [d]).length),
                    q[g] && k.push(e);
                    k.length && s.push({
                        elem: d,
                        matches: k
                    })
                }
                m.length > h && s.push({
                    elem: this,
                    matches: m.slice(h)
                });
                for (b = 0; b < s.length && !a.isPropagationStopped(); b++) {
                    q = s[b];
                    a.currentTarget = q.elem;
                    for (d = 0; d < q.matches.length && !a.isImmediatePropagationStopped(); d++) if (e = q.matches[d], r || !a.namespace && !e.namespace || a.namespace_re && a.namespace_re.test(e.namespace)) a.data = e.data,
                    a.handleObj = e,
                    e = ((c.event.special[e.origType] || {}).handle || e.handler).apply(q.elem, p),
                    e !== n && (a.result = e, !1 === e && (a.preventDefault(), a.stopPropagation()))
                }
                t.postDispatch && t.postDispatch.call(this, a);
                return a.result
            }
        },
        props: "attrChange attrName relatedNode srcElement altKey bubbles cancelable ctrlKey currentTarget eventPhase metaKey relatedTarget shiftKey target timeStamp view which".split(" "),
        fixHooks: {},
        keyHooks: {
            props: ["char", "charCode", "key", "keyCode"],
            filter: function(a, b) {
                null == a.which && (a.which = null != b.charCode ? b.charCode: b.keyCode);
                return a
            }
        },
        mouseHooks: {
            props: "button buttons clientX clientY fromElement offsetX offsetY pageX pageY screenX screenY toElement".split(" "),
            filter: function(a, b) {
                var d, c, e = b.button,
                k = b.fromElement;
                null == a.pageX && null != b.clientX && (d = a.target.ownerDocument || u, c = d.documentElement, d = d.body, a.pageX = b.clientX + (c && c.scrollLeft || d && d.scrollLeft || 0) - (c && c.clientLeft || d && d.clientLeft || 0), a.pageY = b.clientY + (c && c.scrollTop || d && d.scrollTop || 0) - (c && c.clientTop || d && d.clientTop || 0)); ! a.relatedTarget && k && (a.relatedTarget = k === a.target ? b.toElement: k); ! a.which && e !== n && (a.which = e & 1 ? 1 : e & 2 ? 3 : e & 4 ? 2 : 0);
                return a
            }
        },
        fix: function(a) {
            if (a[c.expando]) return a;
            var b, d, e = a,
            f = c.event.fixHooks[a.type] || {},
            k = f.props ? this.props.concat(f.props) : this.props;
            a = c.Event(e);
            for (b = k.length; b;) d = k[--b],
            a[d] = e[d];
            a.target || (a.target = e.srcElement || u);
            3 === a.target.nodeType && (a.target = a.target.parentNode);
            a.metaKey = !!a.metaKey;
            return f.filter ? f.filter(a, e) : a
        },
        special: {
            load: {
                noBubble: !0
            },
            focus: {
                delegateType: "focusin"
            },
            blur: {
                delegateType: "focusout"
            },
            beforeunload: {
                setup: function(a, b, d) {
                    c.isWindow(this) && (this.onbeforeunload = d)
                },
                teardown: function(a, b) {
                    this.onbeforeunload === b && (this.onbeforeunload = null)
                }
            }
        },
        simulate: function(a, b, d, e) {
            a = c.extend(new c.Event, d, {
                type: a,
                isSimulated: !0,
                originalEvent: {}
            });
            e ? c.event.trigger(a, null, b) : c.event.dispatch.call(b, a);
            a.isDefaultPrevented() && d.preventDefault()
        }
    };
    c.event.handle = c.event.dispatch;
    c.removeEvent = u.removeEventListener ?
    function(a, b, d) {
        a.removeEventListener && a.removeEventListener(b, d, !1)
    }: function(a, b, d) {
        b = "on" + b;
        a.detachEvent && ("undefined" === typeof a[b] && (a[b] = null), a.detachEvent(b, d))
    };
    c.Event = function(a, b) {
        if (! (this instanceof c.Event)) return new c.Event(a, b);
        a && a.type ? (this.originalEvent = a, this.type = a.type, this.isDefaultPrevented = a.defaultPrevented || !1 === a.returnValue || a.getPreventDefault && a.getPreventDefault() ? r: p) : this.type = a;
        b && c.extend(this, b);
        this.timeStamp = a && a.timeStamp || c.now();
        this[c.expando] = !0
    };
    c.Event.prototype = {
        preventDefault: function() {
            this.isDefaultPrevented = r;
            var a = this.originalEvent;
            a && (a.preventDefault ? a.preventDefault() : a.returnValue = !1)
        },
        stopPropagation: function() {
            this.isPropagationStopped = r;
            var a = this.originalEvent;
            a && (a.stopPropagation && a.stopPropagation(), a.cancelBubble = !0)
        },
        stopImmediatePropagation: function() {
            this.isImmediatePropagationStopped = r;
            this.stopPropagation()
        },
        isDefaultPrevented: p,
        isPropagationStopped: p,
        isImmediatePropagationStopped: p
    };
    c.each({
        mouseenter: "mouseover",
        mouseleave: "mouseout"
    },
    function(a, b) {
        c.event.special[a] = {
            delegateType: b,
            bindType: b,
            handle: function(a) {
                var e, f = a.relatedTarget,
                k = a.handleObj;
                if (!f || f !== this && !c.contains(this, f)) a.type = k.origType,
                e = k.handler.apply(this, arguments),
                a.type = b;
                return e
            }
        }
    });
    c.support.submitBubbles || (c.event.special.submit = {
        setup: function() {
            if (c.nodeName(this, "form")) return ! 1;
            c.event.add(this, "click._submit keypress._submit",
            function(a) {
                a = a.target;
                if ((a = c.nodeName(a, "input") || c.nodeName(a, "button") ? a.form: n) && !c._data(a, "_submit_attached")) c.event.add(a, "submit._submit",
                function(a) {
                    a._submit_bubble = !0
                }),
                c._data(a, "_submit_attached", !0)
            })
        },
        postDispatch: function(a) {
            a._submit_bubble && (delete a._submit_bubble, this.parentNode && !a.isTrigger && c.event.simulate("submit", this.parentNode, a, !0))
        },
        teardown: function() {
            if (c.nodeName(this, "form")) return ! 1;
            c.event.remove(this, "._submit")
        }
    });
    c.support.changeBubbles || (c.event.special.change = {
        setup: function() {
            if (Ca.test(this.nodeName)) {
                if ("checkbox" === this.type || "radio" === this.type) c.event.add(this, "propertychange._change",
                function(a) {
                    "checked" === a.originalEvent.propertyName && (this._just_changed = !0)
                }),
                c.event.add(this, "click._change",
                function(a) {
                    this._just_changed && !a.isTrigger && (this._just_changed = !1);
                    c.event.simulate("change", this, a, !0)
                });
                return ! 1
            }
            c.event.add(this, "beforeactivate._change",
            function(a) {
                a = a.target;
                Ca.test(a.nodeName) && !c._data(a, "_change_attached") && (c.event.add(a, "change._change",
                function(a) {
                    this.parentNode && (!a.isSimulated && !a.isTrigger) && c.event.simulate("change", this.parentNode, a, !0)
                }), c._data(a, "_change_attached", !0))
            })
        },
        handle: function(a) {
            var b = a.target;
            if (this !== b || a.isSimulated || a.isTrigger || "radio" !== b.type && "checkbox" !== b.type) return a.handleObj.handler.apply(this, arguments)
        },
        teardown: function() {
            c.event.remove(this, "._change");
            return ! Ca.test(this.nodeName)
        }
    });
    c.support.focusinBubbles || c.each({
        focus: "focusin",
        blur: "focusout"
    },
    function(a, b) {
        var d = 0,
        e = function(a) {
            c.event.simulate(b, a.target, c.event.fix(a), !0)
        };
        c.event.special[b] = {
            setup: function() {
                0 === d++&&u.addEventListener(a, e, !0)
            },
            teardown: function() {
                0 === --d && u.removeEventListener(a, e, !0)
            }
        }
    });
    c.fn.extend({
        on: function(a, b, d, e, f) {
            var k, g;
            if ("object" === typeof a) {
                "string" !== typeof b && (d = d || b, b = n);
                for (g in a) this.on(g, b, d, a[g], f);
                return this
            }
            null == d && null == e ? (e = b, d = b = n) : null == e && ("string" === typeof b ? (e = d, d = n) : (e = d, d = b, b = n));
            if (!1 === e) e = p;
            else if (!e) return this;
            1 === f && (k = e, e = function(a) {
                c().off(a);
                return k.apply(this, arguments)
            },
            e.guid = k.guid || (k.guid = c.guid++));
            return this.each(function() {
                c.event.add(this, a, e, d, b)
            })
        },
        one: function(a, b, d, c) {
            return this.on(a, b, d, c, 1)
        },
        off: function(a, b, d) {
            var e;
            if (a && a.preventDefault && a.handleObj) return e = a.handleObj,
            c(a.delegateTarget).off(e.namespace ? e.origType + "." + e.namespace: e.origType, e.selector, e.handler),
            this;
            if ("object" === typeof a) {
                for (e in a) this.off(e, b, a[e]);
                return this
            }
            if (!1 === b || "function" === typeof b) d = b,
            b = n; ! 1 === d && (d = p);
            return this.each(function() {
                c.event.remove(this, a, d, b)
            })
        },
        bind: function(a, b, d) {
            return this.on(a, null, b, d)
        },
        unbind: function(a, b) {
            return this.off(a, null, b)
        },
        live: function(a, b, d) {
            c(this.context).on(a, this.selector, b, d);
            return this
        },
        die: function(a, b) {
            c(this.context).off(a, this.selector || "**", b);
            return this
        },
        delegate: function(a, b, d, c) {
            return this.on(b, a, d, c)
        },
        undelegate: function(a, b, d) {
            return 1 === arguments.length ? this.off(a, "**") : this.off(b, a || "**", d)
        },
        trigger: function(a, b) {
            return this.each(function() {
                c.event.trigger(a, b, this)
            })
        },
        triggerHandler: function(a, b) {
            if (this[0]) return c.event.trigger(a, b, this[0], !0)
        },
        toggle: function(a) {
            var b = arguments,
            d = a.guid || c.guid++,
            e = 0,
            f = function(d) {
                var f = (c._data(this, "lastToggle" + a.guid) || 0) % e;
                c._data(this, "lastToggle" + a.guid, f + 1);
                d.preventDefault();
                return b[f].apply(this, arguments) || !1
            };
            for (f.guid = d; e < b.length;) b[e++].guid = d;
            return this.click(f)
        },
        hover: function(a, b) {
            return this.mouseenter(a).mouseleave(b || a)
        }
    });
    c.each("blur focus focusin focusout load resize scroll unload click dblclick mousedown mouseup mousemove mouseover mouseout mouseenter mouseleave change select submit keydown keypress keyup error contextmenu".split(" "),
    function(a, b) {
        c.fn[b] = function(a, c) {
            null == c && (c = a, a = null);
            return 0 < arguments.length ? this.on(b, null, a, c) : this.trigger(b)
        };
        $b.test(b) && (c.event.fixHooks[b] = c.event.keyHooks);
        ac.test(b) && (c.event.fixHooks[b] = c.event.mouseHooks)
    }); (function(a, b) {
        function d(a, b, d, c) {
            d = d || [];
            b = b || V;
            var e, l, f, k, q = b.nodeType;
            if (!a || "string" !== typeof a) return d;
            if (1 !== q && 9 !== q) return [];
            f = z(b);
            if (!f && !c && (e = S.exec(a))) if (k = e[1]) if (9 === q) if ((l = b.getElementById(k)) && l.parentNode) {
                if (l.id === k) return d.push(l),
                d
            } else return d;
            else {
                if (b.ownerDocument && (l = b.ownerDocument.getElementById(k)) && x(b, l) && l.id === k) return d.push(l),
                d
            } else {
                if (e[2]) return C.apply(d, ga.call(b.getElementsByTagName(a), 0)),
                d;
                if ((k = e[3]) && W && b.getElementsByClassName) return C.apply(d, ga.call(b.getElementsByClassName(k), 0)),
                d
            }
            return y(a.replace(Y, "$1"), b, d, c, f)
        }
        function e(a) {
            return function(b) {
                return "input" === b.nodeName.toLowerCase() && b.type === a
            }
        }
        function f(a) {
            return function(b) {
                var d = b.nodeName.toLowerCase();
                return ("input" === d || "button" === d) && b.type === a
            }
        }
        function k(a) {
            return T(function(b) {
                b = +b;
                return T(function(d, c) {
                    for (var e, l = a([], d.length, b), f = l.length; f--;) if (d[e = l[f]]) d[e] = !(c[e] = d[e])
                })
            })
        }
        function g(a, b, d) {
            if (a === b) return d;
            for (a = a.nextSibling; a;) {
                if (a === b) return - 1;
                a = a.nextSibling
            }
            return 1
        }
        function m(a, b) {
            var c, e, l, f, k, q, g;
            if (k = fb[H][a + " "]) return b ? 0 : k.slice(0);
            k = a;
            q = [];
            for (g = w.preFilter; k;) {
                if (!c || (e = Q.exec(k))) e && (k = k.slice(e[0].length) || k),
                q.push(l = []);
                c = !1;
                if (e = ma.exec(k)) l.push(c = new gb(e.shift())),
                k = k.slice(c.length),
                c.type = e[0].replace(Y, " ");
                for (f in w.filter) if ((e = O[f].exec(k)) && (!g[f] || (e = g[f](e)))) l.push(c = new gb(e.shift())),
                k = k.slice(c.length),
                c.type = f,
                c.matches = e;
                if (!c) break
            }
            return b ? k.length: k ? d.error(a) : fb(a, q).slice(0)
        }
        function h(a, b, d) {
            var c = b.dir,
            e = d && "parentNode" === b.dir,
            l = bc++;
            return b.first ?
            function(b, d, l) {
                for (; b = b[c];) if (e || 1 === b.nodeType) return a(b, d, l)
            }: function(b, d, f) {
                if (f) for (; b = b[c];) {
                    if ((e || 1 === b.nodeType) && a(b, d, f)) return b
                } else for (var k, q = ka + " " + l + " ",
                g = q + N; b = b[c];) if (e || 1 === b.nodeType) {
                    if ((k = b[H]) === g) return b.sizset;
                    if ("string" === typeof k && 0 === k.indexOf(q)) {
                        if (b.sizset) return b
                    } else {
                        b[H] = g;
                        if (a(b, d, f)) return b.sizset = !0,
                        b;
                        b.sizset = !1
                    }
                }
            }
        }
        function p(a) {
            return 1 < a.length ?
            function(b, d, c) {
                for (var e = a.length; e--;) if (!a[e](b, d, c)) return ! 1;
                return ! 0
            }: a[0]
        }
        function n(a, b, d, c, e) {
            for (var l, f = [], k = 0, q = a.length, g = null != b; k < q; k++) if (l = a[k]) if (!d || d(l, c, e)) f.push(l),
            g && b.push(k);
            return f
        }
        function r(a, b, c, e, l, f) {
            e && !e[H] && (e = r(e));
            l && !l[H] && (l = r(l, f));
            return T(function(f, k, q, g) {
                var m, h, A = [],
                p = [],
                r = k.length,
                s;
                if (! (s = f)) {
                    s = b || "*";
                    for (var t = q.nodeType ? [q] : q, eb = [], u = 0, L = t.length; u < L; u++) d(s, t[u], eb);
                    s = eb
                }
                s = a && (f || !b) ? n(s, A, a, q, g) : s;
                t = c ? l || (f ? a: r || e) ? [] : k: s;
                c && c(s, t, q, g);
                if (e) {
                    m = n(t, p);
                    e(m, [], q, g);
                    for (q = m.length; q--;) if (h = m[q]) t[p[q]] = !(s[p[q]] = h)
                }
                if (f) {
                    if (l || a) {
                        if (l) {
                            m = [];
                            for (q = t.length; q--;) if (h = t[q]) m.push(s[q] = h);
                            l(null, t = [], m, g)
                        }
                        for (q = t.length; q--;) if ((h = t[q]) && -1 < (m = l ? E.call(f, h) : A[q])) f[m] = !(k[m] = h)
                    }
                } else t = n(t === k ? t.splice(r, t.length) : t),
                l ? l(null, k, t, g) : C.apply(k, t)
            })
        }
        function s(a) {
            var b, d, c, e = a.length,
            l = w.relative[a[0].type];
            d = l || w.relative[" "];
            for (var f = l ? 1 : 0, k = h(function(a) {
                return a === b
            },
            d, !0), q = h(function(a) {
                return - 1 < E.call(b, a)
            },
            d, !0), g = [function(a, d, c) {
                return ! l && (c || d !== ua) || ((b = d).nodeType ? k(a, d, c) : q(a, d, c))
            }]; f < e; f++) if (d = w.relative[a[f].type]) g = [h(p(g), d)];
            else {
                d = w.filter[a[f].type].apply(null, a[f].matches);
                if (d[H]) {
                    for (c = ++f; c < e && !w.relative[a[c].type]; c++);
                    return r(1 < f && p(g), 1 < f && a.slice(0, f - 1).join("").replace(Y, "$1"), d, f < c && s(a.slice(f, c)), c < e && s(a = a.slice(c)), c < e && a.join(""))
                }
                g.push(d)
            }
            return p(g)
        }
        function u(a, b) {
            var c = 0 < b.length,
            e = 0 < a.length,
            l = function(f, k, q, g, m) {
                var h, A, p = [],
                r = 0,
                t = "0",
                s = f && [],
                u = null != m,
                L = ua,
                Cb = f || e && w.find.TAG("*", m && k.parentNode || k),
                y = ka += null == L ? 1 : Math.E;
                u && (ua = k !== V && k, N = l.el);
                for (; null != (m = Cb[t]); t++) {
                    if (e && m) {
                        for (h = 0; A = a[h]; h++) if (A(m, k, q)) {
                            g.push(m);
                            break
                        }
                        u && (ka = y, N = ++l.el)
                    }
                    c && ((m = !A && m) && r--, f && s.push(m))
                }
                r += t;
                if (c && t !== r) {
                    for (h = 0; A = b[h]; h++) A(s, p, k, q);
                    if (f) {
                        if (0 < r) for (; t--;) ! s[t] && !p[t] && (p[t] = K.call(g));
                        p = n(p)
                    }
                    C.apply(g, p);
                    u && (!f && 0 < p.length && 1 < r + b.length) && d.uniqueSort(g)
                }
                u && (ka = y, ua = L);
                return s
            };
            l.el = 0;
            return c ? T(l) : l
        }
        function y(a, b, d, c, e) {
            var l, f, k, q, g = m(a);
            if (!c && 1 === g.length) {
                f = g[0] = g[0].slice(0);
                if (2 < f.length && "ID" === (k = f[0]).type && 9 === b.nodeType && !e && w.relative[f[1].type]) {
                    b = w.find.ID(k.matches[0].replace(J, ""), b, e)[0];
                    if (!b) return d;
                    a = a.slice(f.shift().length)
                }
                for (l = O.POS.test(a) ? -1 : f.length - 1; 0 <= l; l--) {
                    k = f[l];
                    if (w.relative[q = k.type]) break;
                    if (q = w.find[q]) if (c = q(k.matches[0].replace(J, ""), ea.test(f[0].type) && b.parentNode || b, e)) {
                        f.splice(l, 1);
                        a = c.length && f.join("");
                        if (!a) return C.apply(d, ga.call(c, 0)),
                        d;
                        break
                    }
                }
            }
            I(a, g)(c, b, e, d, ea.test(a));
            return d
        }
        function v() {}
        var N, F, w, va, z, x, I, M, la, ua, hb = !0,
        H = ("sizcache" + Math.random()).replace(".", ""),
        gb = String,
        V = a.document,
        P = V.documentElement,
        ka = 0,
        bc = 0,
        K = [].pop,
        C = [].push,
        ga = [].slice,
        E = [].indexOf ||
        function(a) {
            for (var b = 0,
            d = this.length; b < d; b++) if (this[b] === a) return b;
            return - 1
        },
        T = function(a, b) {
            a[H] = null == b || b;
            return a
        },
        ba = function() {
            var a = {},
            b = [];
            return T(function(d, c) {
                b.push(d) > w.cacheLength && delete a[b.shift()];
                return a[d + " "] = c
            },
            a)
        },
        B = ba(),
        fb = ba(),
        G = ba(),
        ba = "\\[[\\x20\\t\\r\\n\\f]*((?:\\\\.|[-\\w]|[^\\x00-\\xa0])+)[\\x20\\t\\r\\n\\f]*(?:([*^$|!~]?=)[\\x20\\t\\r\\n\\f]*(?:(['\"])((?:\\\\.|[^\\\\])*?)\\3|(" + "(?:\\\\.|[-\\w]|[^\\x00-\\xa0])+".replace("w", "w#") + ")|)|)[\\x20\\t\\r\\n\\f]*\\]",
        D = ":((?:\\\\.|[-\\w]|[^\\x00-\\xa0])+)(?:\\((?:(['\"])((?:\\\\.|[^\\\\])*?)\\2|([^()[\\]]*|(?:(?:" + ba + ")|[^:]|\\\\.)*|.*))\\)|)",
        Y = RegExp("^[\\x20\\t\\r\\n\\f]+|((?:^|[^\\\\])(?:\\\\.)*)[\\x20\\t\\r\\n\\f]+$", "g"),
        Q = /^[\x20\t\r\n\f]*,[\x20\t\r\n\f]*/,
        ma = /^[\x20\t\r\n\f]*([\x20\t\r\n\f>+~])[\x20\t\r\n\f]*/,
        na = RegExp(D),
        S = /^(?:#([\w\-]+)|(\w+)|\.([\w\-]+))$/,
        ea = /[\x20\t\r\n\f]*[+~]/,
        U = /h\d/i,
        X = /input|select|textarea|button/i,
        J = /\\(?!\\)/g,
        O = {
            ID: /^#((?:\\.|[-\w]|[^\x00-\xa0])+)/,
            CLASS: /^\.((?:\\.|[-\w]|[^\x00-\xa0])+)/,
            NAME: /^\[name=['"]?((?:\\.|[-\w]|[^\x00-\xa0])+)['"]?\]/,
            TAG: RegExp("^(" + "(?:\\\\.|[-\\w]|[^\\x00-\\xa0])+".replace("w", "w*") + ")"),
            ATTR: RegExp("^" + ba),
            PSEUDO: RegExp("^" + D),
            POS: /:(even|odd|eq|gt|lt|nth|first|last)(?:\([\x20\t\r\n\f]*((?:-\d)?\d*)[\x20\t\r\n\f]*\)|)(?=[^-]|$)/i,
            CHILD: RegExp("^:(only|nth|first|last)-child(?:\\([\\x20\\t\\r\\n\\f]*(even|odd|(([+-]|)(\\d*)n|)[\\x20\\t\\r\\n\\f]*(?:([+-]|)[\\x20\\t\\r\\n\\f]*(\\d+)|))[\\x20\\t\\r\\n\\f]*\\)|)", "i"),
            needsContext: RegExp("^[\\x20\\t\\r\\n\\f]*[>+~]|:(even|odd|eq|gt|lt|nth|first|last)(?:\\([\\x20\\t\\r\\n\\f]*((?:-\\d)?\\d*)[\\x20\\t\\r\\n\\f]*\\)|)(?=[^-]|$)", "i")
        },
        ca = function(a) {
            var b = V.createElement("div");
            try {
                return a(b)
            } catch(d) {
                return ! 1
            } finally {}
        },
        ba = ca(function(a) {
            a.appendChild(V.createComment(""));
            return ! a.getElementsByTagName("*").length
        }),
        ia = ca(function(a) {
            a.innerHTML = "<a href='#'></a>";
            return a.firstChild && "undefined" !== typeof a.firstChild.getAttribute && "#" === a.firstChild.getAttribute("href")
        }),
        Z = ca(function(a) {
            a.innerHTML = "<select></select>";
            a = typeof a.lastChild.getAttribute("multiple");
            return "boolean" !== a && "string" !== a
        }),
        W = ca(function(a) {
            a.innerHTML = "<div class='hidden e'></div><div class='hidden'></div>";
            if (!a.getElementsByClassName || !a.getElementsByClassName("e").length) return ! 1;
            a.lastChild.className = "e";
            return 2 === a.getElementsByClassName("e").length
        }),
        aa = ca(function(a) {
            a.id = H + 0;
            a.innerHTML = "<a name='" + H + "'></a><div name='" + H + "'></div>";
            P.insertBefore(a, P.firstChild);
            var b = V.getElementsByName && V.getElementsByName(H).length === 2 + V.getElementsByName(H + 0).length;
            F = !V.getElementById(H);
            P.removeChild(a);
            return b
        });
        try {
            ga.call(P.childNodes, 0)[0].nodeType
        } catch(da) {
            ga = function(a) {
                for (var b, d = []; b = this[a]; a++) d.push(b);
                return d
            }
        }
        d.matches = function(a, b) {
            return d(a, null, null, b)
        };
        d.matchesSelector = function(a, b) {
            return 0 < d(b, null, null, [a]).length
        };
        va = d.getText = function(a) {
            var b, d = "",
            c = 0;
            if (b = a.nodeType) if (1 === b || 9 === b || 11 === b) {
                if ("string" === typeof a.textContent) return a.textContent;
                for (a = a.firstChild; a; a = a.nextSibling) d += va(a)
            } else {
                if (3 === b || 4 === b) return a.nodeValue
            } else for (; b = a[c]; c++) d += va(b);
            return d
        };
        z = d.isXML = function(a) {
            return (a = a && (a.ownerDocument || a).documentElement) ? "HTML" !== a.nodeName: !1
        };
        x = d.contains = P.contains ?
        function(a, b) {
            var d = 9 === a.nodeType ? a.documentElement: a,
            c = b && b.parentNode;
            return a === c || !(!c || !(1 === c.nodeType && d.contains && d.contains(c)))
        }: P.compareDocumentPosition ?
        function(a, b) {
            return b && !!(a.compareDocumentPosition(b) & 16)
        }: function(a, b) {
            for (; b = b.parentNode;) if (b === a) return ! 0;
            return ! 1
        };
        d.attr = function(a, b) {
            var d, c = z(a);
            c || (b = b.toLowerCase());
            return (d = w.attrHandle[b]) ? d(a) : c || Z ? a.getAttribute(b) : (d = a.getAttributeNode(b)) ? "boolean" === typeof a[b] ? a[b] ? b: null: d.specified ? d.value: null: null
        };
        w = d.selectors = {
            cacheLength: 50,
            createPseudo: T,
            match: O,
            attrHandle: ia ? {}: {
                href: function(a) {
                    return a.getAttribute("href", 2)
                },
                type: function(a) {
                    return a.getAttribute("type")
                }
            },
            find: {
                ID: F ?
                function(a, b, d) {
                    if ("undefined" !== typeof b.getElementById && !d) return (a = b.getElementById(a)) && a.parentNode ? [a] : []
                }: function(a, d, c) {
                    if ("undefined" !== typeof d.getElementById && !c) return (d = d.getElementById(a)) ? d.id === a || "undefined" !== typeof d.getAttributeNode && d.getAttributeNode("id").value === a ? [d] : b: []
                },
                TAG: ba ?
                function(a, b) {
                    if ("undefined" !== typeof b.getElementsByTagName) return b.getElementsByTagName(a)
                }: function(a, b) {
                    var d = b.getElementsByTagName(a);
                    if ("*" === a) {
                        for (var c, e = [], l = 0; c = d[l]; l++) 1 === c.nodeType && e.push(c);
                        return e
                    }
                    return d
                },
                NAME: aa &&
                function(a, b) {
                    if ("undefined" !== typeof b.getElementsByName) return b.getElementsByName(name)
                },
                CLASS: W &&
                function(a, b, d) {
                    if ("undefined" !== typeof b.getElementsByClassName && !d) return b.getElementsByClassName(a)
                }
            },
            relative: {
                ">": {
                    dir: "parentNode",
                    first: !0
                },
                " ": {
                    dir: "parentNode"
                },
                "+": {
                    dir: "previousSibling",
                    first: !0
                },
                "~": {
                    dir: "previousSibling"
                }
            },
            preFilter: {
                ATTR: function(a) {
                    a[1] = a[1].replace(J, "");
                    a[3] = (a[4] || a[5] || "").replace(J, "");
                    "~=" === a[2] && (a[3] = " " + a[3] + " ");
                    return a.slice(0, 4)
                },
                CHILD: function(a) {
                    a[1] = a[1].toLowerCase();
                    "nth" === a[1] ? (a[2] || d.error(a[0]), a[3] = +(a[3] ? a[4] + (a[5] || 1) : 2 * ("even" === a[2] || "odd" === a[2])), a[4] = +(a[6] + a[7] || "odd" === a[2])) : a[2] && d.error(a[0]);
                    return a
                },
                PSEUDO: function(a) {
                    var b, d;
                    if (O.CHILD.test(a[0])) return null;
                    if (a[3]) a[2] = a[3];
                    else if (b = a[4]) {
                        if (na.test(b) && (d = m(b, !0)) && (d = b.indexOf(")", b.length - d) - b.length)) b = b.slice(0, d),
                        a[0] = a[0].slice(0, d);
                        a[2] = b
                    }
                    return a.slice(0, 3)
                }
            },
            filter: {
                ID: F ?
                function(a) {
                    a = a.replace(J, "");
                    return function(b) {
                        return b.getAttribute("id") === a
                    }
                }: function(a) {
                    a = a.replace(J, "");
                    return function(b) {
                        return (b = "undefined" !== typeof b.getAttributeNode && b.getAttributeNode("id")) && b.value === a
                    }
                },
                TAG: function(a) {
                    if ("*" === a) return function() {
                        return ! 0
                    };
                    a = a.replace(J, "").toLowerCase();
                    return function(b) {
                        return b.nodeName && b.nodeName.toLowerCase() === a
                    }
                },
                CLASS: function(a) {
                    var b = B[H][a + " "];
                    return b || (b = RegExp("(^|[\\x20\\t\\r\\n\\f])" + a + "([\\x20\\t\\r\\n\\f]|$)")) && B(a,
                    function(a) {
                        return b.test(a.className || "undefined" !== typeof a.getAttribute && a.getAttribute("class") || "")
                    })
                },
                ATTR: function(a, b, c) {
                    return function(e, l) {
                        var f = d.attr(e, a);
                        if (null == f) return "!=" === b;
                        if (!b) return ! 0;
                        f += "";
                        return "=" === b ? f === c: "!=" === b ? f !== c: "^=" === b ? c && 0 === f.indexOf(c) : "*=" === b ? c && -1 < f.indexOf(c) : "$=" === b ? c && f.substr(f.length - c.length) === c: "~=" === b ? -1 < (" " + f + " ").indexOf(c) : "|=" === b ? f === c || f.substr(0, c.length + 1) === c + "-": !1
                    }
                },
                CHILD: function(a, b, d, c) {
                    return "nth" === a ?
                    function(a) {
                        var b, e;
                        b = a.parentNode;
                        if (1 === d && 0 === c) return ! 0;
                        if (b) {
                            e = 0;
                            for (b = b.firstChild; b && !(1 === b.nodeType && (e++, a === b)); b = b.nextSibling);
                        }
                        e -= c;
                        return e === d || 0 === e % d && 0 <= e / d
                    }: function(b) {
                        var d = b;
                        switch (a) {
                        case "only":
                        case "first":
                            for (; d = d.previousSibling;) if (1 === d.nodeType) return ! 1;
                            if ("first" === a) return ! 0;
                            d = b;
                        case "last":
                            for (; d = d.nextSibling;) if (1 === d.nodeType) return ! 1;
                            return ! 0
                        }
                    }
                },
                PSEUDO: function(a, b) {
                    var c, e = w.pseudos[a] || w.setFilters[a.toLowerCase()] || d.error("unsupported pseudo: " + a);
                    return e[H] ? e(b) : 1 < e.length ? (c = [a, a, "", b], w.setFilters.hasOwnProperty(a.toLowerCase()) ? T(function(a, d) {
                        for (var c, l = e(a, b), f = l.length; f--;) c = E.call(a, l[f]),
                        a[c] = !(d[c] = l[f])
                    }) : function(a) {
                        return e(a, 0, c)
                    }) : e
                }
            },
            pseudos: {
                not: T(function(a) {
                    var b = [],
                    d = [],
                    c = I(a.replace(Y, "$1"));
                    return c[H] ? T(function(a, b, d, e) {
                        e = c(a, null, e, []);
                        for (var l = a.length; l--;) if (d = e[l]) a[l] = !(b[l] = d)
                    }) : function(a, e, l) {
                        b[0] = a;
                        c(b, null, l, d);
                        return ! d.pop()
                    }
                }),
                has: T(function(a) {
                    return function(b) {
                        return 0 < d(a, b).length
                    }
                }),
                contains: T(function(a) {
                    return function(b) {
                        return - 1 < (b.textContent || b.innerText || va(b)).indexOf(a)
                    }
                }),
                enabled: function(a) {
                    return ! 1 === a.disabled
                },
                disabled: function(a) {
                    return ! 0 === a.disabled
                },
                checked: function(a) {
                    var b = a.nodeName.toLowerCase();
                    return "input" === b && !!a.checked || "option" === b && !!a.selected
                },
                selected: function(a) {
                    a.parentNode && a.parentNode.selectedIndex;
                    return ! 0 === a.selected
                },
                parent: function(a) {
                    return ! w.pseudos.empty(a)
                },
                empty: function(a) {
                    var b;
                    for (a = a.firstChild; a;) {
                        if ("@" < a.nodeName || 3 === (b = a.nodeType) || 4 === b) return ! 1;
                        a = a.nextSibling
                    }
                    return ! 0
                },
                header: function(a) {
                    return U.test(a.nodeName)
                },
                text: function(a) {
                    var b, d;
                    return "input" === a.nodeName.toLowerCase() && "text" === (b = a.type) && (null == (d = a.getAttribute("type")) || d.toLowerCase() === b)
                },
                radio: e("radio"),
                checkbox: e("checkbox"),
                file: e("file"),
                password: e("password"),
                image: e("image"),
                submit: f("submit"),
                reset: f("reset"),
                button: function(a) {
                    var b = a.nodeName.toLowerCase();
                    return "input" === b && "button" === a.type || "button" === b
                },
                input: function(a) {
                    return X.test(a.nodeName)
                },
                focus: function(a) {
                    var b = a.ownerDocument;
                    return a === b.activeElement && (!b.hasFocus || b.hasFocus()) && !(!a.type && !a.href && !~a.tabIndex)
                },
                active: function(a) {
                    return a === a.ownerDocument.activeElement
                },
                first: k(function() {
                    return [0]
                }),
                last: k(function(a, b) {
                    return [b - 1]
                }),
                eq: k(function(a, b, d) {
                    return [0 > d ? d + b: d]
                }),
                even: k(function(a, b) {
                    for (var d = 0; d < b; d += 2) a.push(d);
                    return a
                }),
                odd: k(function(a, b) {
                    for (var d = 1; d < b; d += 2) a.push(d);
                    return a
                }),
                lt: k(function(a, b, d) {
                    for (b = 0 > d ? d + b: d; 0 <= --b;) a.push(b);
                    return a
                }),
                gt: k(function(a, b, d) {
                    for (d = 0 > d ? d + b: d; ++d < b;) a.push(d);
                    return a
                })
            }
        };
        M = P.compareDocumentPosition ?
        function(a, b) {
            return a === b ? (la = !0, 0) : (!a.compareDocumentPosition || !b.compareDocumentPosition ? a.compareDocumentPosition: a.compareDocumentPosition(b) & 4) ? -1 : 1
        }: function(a, b) {
            if (a === b) return la = !0,
            0;
            if (a.sourceIndex && b.sourceIndex) return a.sourceIndex - b.sourceIndex;
            var d, c, e = [],
            l = [];
            d = a.parentNode;
            c = b.parentNode;
            var f = d;
            if (d === c) return g(a, b);
            if (d) {
                if (!c) return 1
            } else return - 1;
            for (; f;) e.unshift(f),
            f = f.parentNode;
            for (f = c; f;) l.unshift(f),
            f = f.parentNode;
            d = e.length;
            c = l.length;
            for (f = 0; f < d && f < c; f++) if (e[f] !== l[f]) return g(e[f], l[f]);
            return f === d ? g(a, l[f], -1) : g(e[f], b, 1)
        }; [0, 0].sort(M);
        hb = !la;
        d.uniqueSort = function(a) {
            var b, d = [],
            c = 1,
            e = 0;
            la = hb;
            a.sort(M);
            if (la) {
                for (; b = a[c]; c++) b === a[c - 1] && (e = d.push(c));
                for (; e--;) a.splice(d[e], 1)
            }
            return a
        };
        d.error = function(a) {
            throw Error("Syntax error, unrecognized expression: " + a);
        };
        I = d.compile = function(a, b) {
            var d, c = [],
            e = [],
            l = G[H][a + " "];
            if (!l) {
                b || (b = m(a));
                for (d = b.length; d--;) l = s(b[d]),
                l[H] ? c.push(l) : e.push(l);
                l = G(a, u(e, c))
            }
            return l
        };
        V.querySelectorAll &&
        function() {
            var a, b = y,
            c = /'|\\/g,
            e = /\=[\x20\t\r\n\f]*([^'"\]]*)[\x20\t\r\n\f]*\]/g,
            l = [":focus"],
            f = [":active"],
            k = P.matchesSelector || P.mozMatchesSelector || P.webkitMatchesSelector || P.oMatchesSelector || P.msMatchesSelector;
            ca(function(a) {
                a.innerHTML = "<select><option selected=''></option></select>";
                a.querySelectorAll("[selected]").length || l.push("\\[[\\x20\\t\\r\\n\\f]*(?:checked|disabled|ismap|multiple|readonly|selected|value)");
                a.querySelectorAll(":checked").length || l.push(":checked")
            });
            ca(function(a) {
                a.innerHTML = "<p test=''></p>";
                a.querySelectorAll("[test^='']").length && l.push("[*^$]=[\\x20\\t\\r\\n\\f]*(?:\"\"|'')");
                a.innerHTML = "<input type='hidden'/>";
                a.querySelectorAll(":enabled").length || l.push(":enabled", ":disabled")
            });
            l = RegExp(l.join("|"));
            y = function(a, d, e, f, k) {
                if (!f && !k && !l.test(a)) {
                    var q, g, h = !0,
                    A = H;
                    g = d;
                    q = 9 === d.nodeType && a;
                    if (1 === d.nodeType && "object" !== d.nodeName.toLowerCase()) {
                        q = m(a); (h = d.getAttribute("id")) ? A = h.replace(c, "\\$&") : d.setAttribute("id", A);
                        A = "[id='" + A + "'] ";
                        for (g = q.length; g--;) q[g] = A + q[g].join("");
                        g = ea.test(a) && d.parentNode || d;
                        q = q.join(",")
                    }
                    if (q) try {
                        return C.apply(e, ga.call(g.querySelectorAll(q), 0)),
                        e
                    } catch(p) {} finally {
                        h || d.removeAttribute("id")
                    }
                }
                return b(a, d, e, f, k)
            };
            k && (ca(function(b) {
                a = k.call(b, "div");
                try {
                    k.call(b, "[test!='']:sizzle"),
                    f.push("!=", D)
                } catch(d) {}
            }), f = RegExp(f.join("|")), d.matchesSelector = function(b, c) {
                c = c.replace(e, "='$1']");
                if (!z(b) && !f.test(c) && !l.test(c)) try {
                    var q = k.call(b, c);
                    if (q || a || b.document && 11 !== b.document.nodeType) return q
                } catch(g) {}
                return 0 < d(c, null, null, [b]).length
            })
        } ();
        w.pseudos.nth = w.pseudos.eq;
        w.filters = v.prototype = w.pseudos;
        w.setFilters = new v;
        d.attr = c.attr;
        c.find = d;
        c.expr = d.selectors;
        c.expr[":"] = c.expr.pseudos;
        c.unique = d.uniqueSort;
        c.text = d.getText;
        c.isXMLDoc = d.isXML;
        c.contains = d.contains
    })(f);
    var cc = /Until$/,
    dc = /^(?:parents|prev(?:Until|All))/,
    zb = /^.[^:#\[\.,]*$/,
    ib = c.expr.match.needsContext,
    ec = {
        children: !0,
        contents: !0,
        next: !0,
        prev: !0
    };
    c.fn.extend({
        find: function(a) {
            var b, d, e, f, k, g, m = this;
            if ("string" !== typeof a) return c(a).filter(function() {
                b = 0;
                for (d = m.length; b < d; b++) if (c.contains(m[b], this)) return ! 0
            });
            g = this.pushStack("", "find", a);
            b = 0;
            for (d = this.length; b < d; b++) if (e = g.length, c.find(a, this[b], g), 0 < b) for (f = e; f < g.length; f++) for (k = 0; k < e; k++) if (g[k] === g[f]) {
                g.splice(f--, 1);
                break
            }
            return g
        },
        has: function(a) {
            var b, d = c(a, this),
            e = d.length;
            return this.filter(function() {
                for (b = 0; b < e; b++) if (c.contains(this, d[b])) return ! 0
            })
        },
        not: function(a) {
            return this.pushStack(G(this, a, !1), "not", a)
        },
        filter: function(a) {
            return this.pushStack(G(this, a, !0), "filter", a)
        },
        is: function(a) {
            return !! a && ("string" === typeof a ? ib.test(a) ? 0 <= c(a, this.context).index(this[0]) : 0 < c.filter(a, this).length: 0 < this.filter(a).length)
        },
        closest: function(a, b) {
            for (var d, e = 0,
            f = this.length,
            k = [], g = ib.test(a) || "string" !== typeof a ? c(a, b || this.context) : 0; e < f; e++) for (d = this[e]; d && d.ownerDocument && d !== b && 11 !== d.nodeType;) {
                if (g ? -1 < g.index(d) : c.find.matchesSelector(d, a)) {
                    k.push(d);
                    break
                }
                d = d.parentNode
            }
            k = 1 < k.length ? c.unique(k) : k;
            return this.pushStack(k, "closest", a)
        },
        index: function(a) {
            return ! a ? this[0] && this[0].parentNode ? this.prevAll().length: -1 : "string" === typeof a ? c.inArray(this[0], c(a)) : c.inArray(a.jquery ? a[0] : a, this)
        },
        add: function(a, b) {
            var d = "string" === typeof a ? c(a, b) : c.makeArray(a && a.nodeType ? [a] : a),
            e = c.merge(this.get(), d);
            return this.pushStack(s(d[0]) || s(e[0]) ? e: c.unique(e))
        },
        addBack: function(a) {
            return this.add(null == a ? this.prevObject: this.prevObject.filter(a))
        }
    });
    c.fn.andSelf = c.fn.addBack;
    c.each({
        parent: function(a) {
            return (a = a.parentNode) && 11 !== a.nodeType ? a: null
        },
        parents: function(a) {
            return c.dir(a, "parentNode")
        },
        parentsUntil: function(a, b, d) {
            return c.dir(a, "parentNode", d)
        },
        next: function(a) {
            return v(a, "nextSibling")
        },
        prev: function(a) {
            return v(a, "previousSibling")
        },
        nextAll: function(a) {
            return c.dir(a, "nextSibling")
        },
        prevAll: function(a) {
            return c.dir(a, "previousSibling")
        },
        nextUntil: function(a, b, d) {
            return c.dir(a, "nextSibling", d)
        },
        prevUntil: function(a, b, d) {
            return c.dir(a, "previousSibling", d)
        },
        siblings: function(a) {
            return c.sibling((a.parentNode || {}).firstChild, a)
        },
        children: function(a) {
            return c.sibling(a.firstChild)
        },
        contents: function(a) {
            return c.nodeName(a, "iframe") ? a.contentDocument || a.contentWindow.document: c.merge([], a.childNodes)
        }
    },
    function(a, b) {
        c.fn[a] = function(d, e) {
            var f = c.map(this, b, d);
            cc.test(a) || (e = d);
            e && "string" === typeof e && (f = c.filter(e, f));
            f = 1 < this.length && !ec[a] ? c.unique(f) : f;
            1 < this.length && dc.test(a) && (f = f.reverse());
            return this.pushStack(f, a, S.call(arguments).join(","))
        }
    });
    c.extend({
        filter: function(a, b, d) {
            d && (a = ":not(" + a + ")");
            return 1 === b.length ? c.find.matchesSelector(b[0], a) ? [b[0]] : [] : c.find.matches(a, b)
        },
        dir: function(a, b, d) {
            var e = [];
            for (a = a[b]; a && 9 !== a.nodeType && (d === n || 1 !== a.nodeType || !c(a).is(d));) 1 === a.nodeType && e.push(a),
            a = a[b];
            return e
        },
        sibling: function(a, b) {
            for (var d = []; a; a = a.nextSibling) 1 === a.nodeType && a !== b && d.push(a);
            return d
        }
    });
    var Ja = "abbr|article|aside|audio|bdi|canvas|data|datalist|details|figcaption|figure|footer|header|hgroup|mark|meter|nav|output|progress|section|summary|time|video",
    fc = / jQuery\d+="(?:null|\d+)"/g,
    Da = /^\s+/,
    jb = /<(?!area|br|col|embed|hr|img|input|link|meta|param)(([\w:]+)[^>]*)\/>/gi,
    kb = /<([\w:]+)/,
    gc = /<tbody/i,
    hc = /<|&#?\w+;/,
    ic = /<(?:script|style|link)/i,
    jc = /<(?:script|object|embed|option|style)/i,
    Ea = RegExp("<(?:" + Ja + ")[\\s/>]", "i"),
    Ka = /^(?:checkbox|radio)$/,
    lb = /checked\s*(?:[^=]|=\s*.checked.)/i,
    kc = /\/(java|ecma)script/i,
    lc = /^\s*<!(?:\[CDATA\[|\-\-)|[\]\-]{2}>\s*$/g,
    B = {
        option: [1, "<select multiple='multiple'>", "</select>"],
        legend: [1, "<fieldset>", "</fieldset>"],
        thead: [1, "<table>", "</table>"],
        tr: [2, "<table><tbody>", "</tbody></table>"],
        td: [3, "<table><tbody><tr>", "</tr></tbody></table>"],
        col: [2, "<table><tbody></tbody><colgroup>", "</colgroup></table>"],
        area: [1, "<map>", "</map>"],
        _default: [0, "", ""]
    },
    mb = y(u),
    Fa = mb.appendChild(u.createElement("div"));
    B.optgroup = B.option;
    B.tbody = B.tfoot = B.colgroup = B.caption = B.thead;
    B.th = B.td;
    c.support.htmlSerialize || (B._default = [1, "X<div>", "</div>"]);
    c.fn.extend({
        text: function(a) {
            return c.access(this,
            function(a) {
                return a === n ? c.text(this) : this.empty().append((this[0] && this[0].ownerDocument || u).createTextNode(a))
            },
            null, a, arguments.length)
        },
        wrapAll: function(a) {
            if (c.isFunction(a)) return this.each(function(b) {
                c(this).wrapAll(a.call(this, b))
            });
            if (this[0]) {
                var b = c(a, this[0].ownerDocument).eq(0).clone(!0);
                this[0].parentNode && b.insertBefore(this[0]);
                b.map(function() {
                    for (var a = this; a.firstChild && 1 === a.firstChild.nodeType;) a = a.firstChild;
                    return a
                }).append(this)
            }
            return this
        },
        wrapInner: function(a) {
            return c.isFunction(a) ? this.each(function(b) {
                c(this).wrapInner(a.call(this, b))
            }) : this.each(function() {
                var b = c(this),
                d = b.contents();
                d.length ? d.wrapAll(a) : b.append(a)
            })
        },
        wrap: function(a) {
            var b = c.isFunction(a);
            return this.each(function(d) {
                c(this).wrapAll(b ? a.call(this, d) : a)
            })
        },
        unwrap: function() {
            return this.parent().each(function() {
                c.nodeName(this, "body") || c(this).replaceWith(this.childNodes)
            }).end()
        },
        append: function() {
            return this.domManip(arguments, !0,
            function(a) { (1 === this.nodeType || 11 === this.nodeType) && this.appendChild(a)
            })
        },
        prepend: function() {
            return this.domManip(arguments, !0,
            function(a) { (1 === this.nodeType || 11 === this.nodeType) && this.insertBefore(a, this.firstChild)
            })
        },
        before: function() {
            if (!s(this[0])) return this.domManip(arguments, !1,
            function(a) {
                this.parentNode.insertBefore(a, this)
            });
            if (arguments.length) {
                var a = c.clean(arguments);
                return this.pushStack(c.merge(a, this), "before", this.selector)
            }
        },
        after: function() {
            if (!s(this[0])) return this.domManip(arguments, !1,
            function(a) {
                this.parentNode.insertBefore(a, this.nextSibling)
            });
            if (arguments.length) {
                var a = c.clean(arguments);
                return this.pushStack(c.merge(this, a), "after", this.selector)
            }
        },
        remove: function(a, b) {
            for (var d, e = 0; null != (d = this[e]); e++) if (!a || c.filter(a, [d]).length) ! b && 1 === d.nodeType && (c.cleanData(d.getElementsByTagName("*")), c.cleanData([d])),
            d.parentNode && d.parentNode.removeChild(d);
            return this
        },
        empty: function() {
            for (var a, b = 0; null != (a = this[b]); b++) for (1 === a.nodeType && c.cleanData(a.getElementsByTagName("*")); a.firstChild;) a.removeChild(a.firstChild);
            return this
        },
        clone: function(a, b) {
            a = null == a ? !1 : a;
            b = null == b ? a: b;
            return this.map(function() {
                return c.clone(this, a, b)
            })
        },
        html: function(a) {
            return c.access(this,
            function(a) {
                var d = this[0] || {},
                e = 0,
                f = this.length;
                if (a === n) return 1 === d.nodeType ? d.innerHTML.replace(fc, "") : n;
                if ("string" === typeof a && !ic.test(a) && (c.support.htmlSerialize || !Ea.test(a)) && (c.support.leadingWhitespace || !Da.test(a)) && !B[(kb.exec(a) || ["", ""])[1].toLowerCase()]) {
                    a = a.replace(jb, "<$1></$2>");
                    try {
                        for (; e < f; e++) d = this[e] || {},
                        1 === d.nodeType && (c.cleanData(d.getElementsByTagName("*")), d.innerHTML = a);
                        d = 0
                    } catch(k) {}
                }
                d && this.empty().append(a)
            },
            null, a, arguments.length)
        },
        replaceWith: function(a) {
            if (!s(this[0])) {
                if (c.isFunction(a)) return this.each(function(b) {
                    var d = c(this),
                    e = d.html();
                    d.replaceWith(a.call(this, b, e))
                });
                "string" !== typeof a && (a = c(a).detach());
                return this.each(function() {
                    var b = this.nextSibling,
                    d = this.parentNode;
                    c(this).remove();
                    b ? c(b).before(a) : c(d).append(a)
                })
            }
            return this.length ? this.pushStack(c(c.isFunction(a) ? a() : a), "replaceWith", a) : this
        },
        detach: function(a) {
            return this.remove(a, !0)
        },
        domManip: function(a, b, d) {
            a = [].concat.apply([], a);
            var e, f, k, g = 0,
            m = a[0],
            h = [],
            p = this.length;
            if (!c.support.checkClone && 1 < p && "string" === typeof m && lb.test(m)) return this.each(function() {
                c(this).domManip(a, b, d)
            });
            if (c.isFunction(m)) return this.each(function(e) {
                var f = c(this);
                a[0] = m.call(this, e, b ? f.html() : n);
                f.domManip(a, b, d)
            });
            if (this[0]) {
                e = c.buildFragment(a, this, h);
                k = e.fragment;
                f = k.firstChild;
                1 === k.childNodes.length && (k = f);
                if (f) {
                    b = b && c.nodeName(f, "tr");
                    for (e = e.cacheable || p - 1; g < p; g++) d.call(b && c.nodeName(this[g], "table") ? this[g].getElementsByTagName("tbody")[0] || this[g].appendChild(this[g].ownerDocument.createElement("tbody")) : this[g], g === e ? k: c.clone(k, !0, !0))
                }
                k = f = null;
                h.length && c.each(h,
                function(a, b) {
                    b.src ? c.ajax ? c.ajax({
                        url: b.src,
                        type: "GET",
                        dataType: "script",
                        async: !1,
                        global: !1,
                        "throws": !0
                    }) : c.error("no ajax") : c.globalEval((b.text || b.textContent || b.innerHTML || "").replace(lc, ""));
                    b.parentNode && b.parentNode.removeChild(b)
                })
            }
            return this
        }
    });
    c.buildFragment = function(a, b, d) {
        var e, f, k, g = a[0];
        b = b || u;
        b = !b.nodeType && b[0] || b;
        b = b.ownerDocument || b;
        if (1 === a.length && "string" === typeof g && 512 > g.length && b === u && "<" === g.charAt(0) && !jc.test(g) && (c.support.checkClone || !lb.test(g)) && (c.support.html5Clone || !Ea.test(g))) f = !0,
        e = c.fragments[g],
        k = e !== n;
        e || (e = b.createDocumentFragment(), c.clean(a, b, e, d), f && (c.fragments[g] = k && e));
        return {
            fragment: e,
            cacheable: f
        }
    };
    c.fragments = {};
    c.each({
        appendTo: "append",
        prependTo: "prepend",
        insertBefore: "before",
        insertAfter: "after",
        replaceAll: "replaceWith"
    },
    function(a, b) {
        c.fn[a] = function(d) {
            var e, f = 0,
            k = [];
            d = c(d);
            var g = d.length;
            e = 1 === this.length && this[0].parentNode;
            if ((null == e || e && 11 === e.nodeType && 1 === e.childNodes.length) && 1 === g) return d[b](this[0]),
            this;
            for (; f < g; f++) e = (0 < f ? this.clone(!0) : this).get(),
            c(d[f])[b](e),
            k = k.concat(e);
            return this.pushStack(k, a, d.selector)
        }
    });
    c.extend({
        clone: function(a, b, d) {
            var e, f, k, g;
            c.support.html5Clone || c.isXMLDoc(a) || !Ea.test("<" + a.nodeName + ">") ? g = a.cloneNode(!0) : (Fa.innerHTML = a.outerHTML, Fa.removeChild(g = Fa.firstChild));
            if ((!c.support.noCloneEvent || !c.support.noCloneChecked) && (1 === a.nodeType || 11 === a.nodeType) && !c.isXMLDoc(a)) {
                F(a, g);
                e = D(a);
                f = D(g);
                for (k = 0; e[k]; ++k) f[k] && F(e[k], f[k])
            }
            if (b && (N(a, g), d)) {
                e = D(a);
                f = D(g);
                for (k = 0; e[k]; ++k) N(e[k], f[k])
            }
            return g
        },
        clean: function(a, b, d, e) {
            var f, k, g, m, h, p, n = b === u && mb,
            r = [];
            if (!b || "undefined" === typeof b.createDocumentFragment) b = u;
            for (f = 0; null != (g = a[f]); f++) if ("number" === typeof g && (g += ""), g) {
                if ("string" === typeof g) if (hc.test(g)) {
                    n = n || y(b);
                    p = b.createElement("div");
                    n.appendChild(p);
                    g = g.replace(jb, "<$1></$2>");
                    k = (kb.exec(g) || ["", ""])[1].toLowerCase();
                    m = B[k] || B._default;
                    h = m[0];
                    for (p.innerHTML = m[1] + g + m[2]; h--;) p = p.lastChild;
                    if (!c.support.tbody) {
                        h = gc.test(g);
                        m = "table" === k && !h ? p.firstChild && p.firstChild.childNodes: "<table>" === m[1] && !h ? p.childNodes: [];
                        for (k = m.length - 1; 0 <= k; --k) c.nodeName(m[k], "tbody") && !m[k].childNodes.length && m[k].parentNode.removeChild(m[k])
                    } ! c.support.leadingWhitespace && Da.test(g) && p.insertBefore(b.createTextNode(Da.exec(g)[0]), p.firstChild);
                    g = p.childNodes;
                    p.parentNode.removeChild(p)
                } else g = b.createTextNode(g);
                g.nodeType ? r.push(g) : c.merge(r, g)
            }
            p && (g = p = n = null);
            if (!c.support.appendChecked) for (f = 0; null != (g = r[f]); f++) c.nodeName(g, "input") ? K(g) : "undefined" !== typeof g.getElementsByTagName && c.grep(g.getElementsByTagName("input"), K);
            if (d) {
                a = function(a) {
                    if (!a.type || kc.test(a.type)) return e ? e.push(a.parentNode ? a.parentNode.removeChild(a) : a) : d.appendChild(a)
                };
                for (f = 0; null != (g = r[f]); f++) if (!c.nodeName(g, "script") || !a(g)) d.appendChild(g),
                "undefined" !== typeof g.getElementsByTagName && (g = c.grep(c.merge([], g.getElementsByTagName("script")), a), r.splice.apply(r, [f + 1, 0].concat(g)), f += g.length)
            }
            return r
        },
        cleanData: function(a, b) {
            for (var d, e, f, k, g = 0,
            m = c.expando,
            h = c.cache,
            p = c.support.deleteExpando,
            n = c.event.special; null != (f = a[g]); g++) if (b || c.acceptData(f)) if (d = (e = f[m]) && h[e]) {
                if (d.events) for (k in d.events) n[k] ? c.event.remove(f, k) : c.removeEvent(f, k, d.handle);
                h[e] && (delete h[e], p ? delete f[m] : f.removeAttribute ? f.removeAttribute(m) : f[m] = null, c.deletedIds.push(e))
            }
        }
    }); (function() {
        var a, b;
        c.uaMatch = function(a) {
            a = a.toLowerCase();
            a = /(chrome)[ \/]([\w.]+)/.exec(a) || /(webkit)[ \/]([\w.]+)/.exec(a) || /(opera)(?:.*version|)[ \/]([\w.]+)/.exec(a) || /(msie) ([\w.]+)/.exec(a) || 0 > a.indexOf("compatible") && /(mozilla)(?:.*? rv:([\w.]+)|)/.exec(a) || [];
            return {
                browser: a[1] || "",
                version: a[2] || "0"
            }
        };
        a = c.uaMatch(Gb.userAgent);
        b = {};
        a.browser && (b[a.browser] = !0, b.version = a.version);
        b.chrome ? b.webkit = !0 : b.webkit && (b.safari = !0);
        c.browser = b;
        c.sub = function() {
            function a(b, c) {
                return new a.fn.init(b, c)
            }
            c.extend(!0, a, this);
            a.superclass = this;
            a.fn = a.prototype = this();
            a.fn.constructor = a;
            a.sub = this.sub;
            a.fn.init = function(e, f) {
                f && (f instanceof c && !(f instanceof a)) && (f = a(f));
                return c.fn.init.call(this, e, f, b)
            };
            a.fn.init.prototype = a.fn;
            var b = a(u);
            return a
        }
    })();
    var z, aa, da, Ga = /alpha\([^)]*\)/i,
    mc = /opacity=([^)]*)/,
    nc = /^(top|right|bottom|left)$/,
    oc = /^(none|table(?!-c[ea]).+)/,
    nb = /^margin/,
    Ab = RegExp("^(" + sa + ")(.*)$", "i"),
    oa = RegExp("^(" + sa + ")(?!px)[a-z%]+$", "i"),
    pc = RegExp("^([-+])=(" + sa + ")", "i"),
    ya = {
        BODY: "block"
    },
    qc = {
        position: "absolute",
        visibility: "hidden",
        display: "block"
    },
    ob = {
        letterSpacing: 0,
        fontWeight: 400
    },
    O = ["Top", "Right", "Bottom", "Left"],
    La = ["Webkit", "O", "Moz", "ms"],
    rc = c.fn.toggle;
    c.fn.extend({
        css: function(a, b) {
            return c.access(this,
            function(a, b, e) {
                return e !== n ? c.style(a, b, e) : c.css(a, b)
            },
            a, b, 1 < arguments.length)
        },
        show: function() {
            return ma(this, !0)
        },
        hide: function() {
            return ma(this)
        },
        toggle: function(a, b) {
            var d = "boolean" === typeof a;
            return c.isFunction(a) && c.isFunction(b) ? rc.apply(this, arguments) : this.each(function() { (d ? a: Q(this)) ? c(this).show() : c(this).hide()
            })
        }
    });
    c.extend({
        cssHooks: {
            opacity: {
                get: function(a, b) {
                    if (b) {
                        var d = z(a, "opacity");
                        return "" === d ? "1": d
                    }
                }
            }
        },
        cssNumber: {
            fillOpacity: !0,
            fontWeight: !0,
            lineHeight: !0,
            opacity: !0,
            orphans: !0,
            widows: !0,
            zIndex: !0,
            zoom: !0
        },
        cssProps: {
            "float": c.support.cssFloat ? "cssFloat": "styleFloat"
        },
        style: function(a, b, d, e) {
            if (a && !(3 === a.nodeType || 8 === a.nodeType || !a.style)) {
                var f, g, h, m = c.camelCase(b),
                p = a.style;
                b = c.cssProps[m] || (c.cssProps[m] = J(p, m));
                h = c.cssHooks[b] || c.cssHooks[m];
                if (d !== n) {
                    g = typeof d;
                    if ("string" === g && (f = pc.exec(d))) d = (f[1] + 1) * f[2] + parseFloat(c.css(a, b)),
                    g = "number";
                    if (! (null == d || "number" === g && isNaN(d))) if ("number" === g && !c.cssNumber[m] && (d += "px"), !h || !("set" in h) || (d = h.set(a, d, e)) !== n) try {
                        p[b] = d
                    } catch(r) {}
                } else return h && "get" in h && (f = h.get(a, !1, e)) !== n ? f: p[b]
            }
        },
        css: function(a, b, d, e) {
            var f, g;
            g = c.camelCase(b);
            b = c.cssProps[g] || (c.cssProps[g] = J(a.style, g)); (g = c.cssHooks[b] || c.cssHooks[g]) && "get" in g && (f = g.get(a, !0, e));
            f === n && (f = z(a, b));
            "normal" === f && b in ob && (f = ob[b]);
            return d || e !== n ? (a = parseFloat(f), d || c.isNumeric(a) ? a || 0 : f) : f
        },
        swap: function(a, b, d) {
            var c, e = {};
            for (c in b) e[c] = a.style[c],
            a.style[c] = b[c];
            d = d.call(a);
            for (c in b) a.style[c] = e[c];
            return d
        }
    });
    f.getComputedStyle ? z = function(a, b) {
        var d, e, g, k, h = f.getComputedStyle(a, null),
        m = a.style;
        h && (d = h.getPropertyValue(b) || h[b], "" === d && !c.contains(a.ownerDocument, a) && (d = c.style(a, b)), oa.test(d) && nb.test(b) && (e = m.width, g = m.minWidth, k = m.maxWidth, m.minWidth = m.maxWidth = m.width = d, d = h.width, m.width = e, m.minWidth = g, m.maxWidth = k));
        return d
    }: u.documentElement.currentStyle && (z = function(a, b) {
        var d, c, e = a.currentStyle && a.currentStyle[b],
        f = a.style;
        null == e && (f && f[b]) && (e = f[b]);
        if (oa.test(e) && !nc.test(b)) {
            d = f.left;
            if (c = a.runtimeStyle && a.runtimeStyle.left) a.runtimeStyle.left = a.currentStyle.left;
            f.left = "fontSize" === b ? "1em": e;
            e = f.pixelLeft + "px";
            f.left = d;
            c && (a.runtimeStyle.left = c)
        }
        return "" === e ? "auto": e
    });
    c.each(["height", "width"],
    function(a, b) {
        c.cssHooks[b] = {
            get: function(a, e, f) {
                if (e) return 0 === a.offsetWidth && oc.test(z(a, "display")) ? c.swap(a, qc,
                function() {
                    return M(a, b, f)
                }) : M(a, b, f)
            },
            set: function(a, e, f) {
                return I(a, e, f ? na(a, b, f, c.support.boxSizing && "border-box" === c.css(a, "boxSizing")) : 0)
            }
        }
    });
    c.support.opacity || (c.cssHooks.opacity = {
        get: function(a, b) {
            return mc.test((b && a.currentStyle ? a.currentStyle.filter: a.style.filter) || "") ? 0.01 * parseFloat(RegExp.$1) + "": b ? "1": ""
        },
        set: function(a, b) {
            var d = a.style,
            e = a.currentStyle,
            f = c.isNumeric(b) ? "alpha(opacity=" + 100 * b + ")": "",
            g = e && e.filter || d.filter || "";
            d.zoom = 1;
            if (1 <= b && ("" === c.trim(g.replace(Ga, "")) && d.removeAttribute) && (d.removeAttribute("filter"), e && !e.filter)) return;
            d.filter = Ga.test(g) ? g.replace(Ga, f) : g + " " + f
        }
    });
    c(function() {
        c.support.reliableMarginRight || (c.cssHooks.marginRight = {
            get: function(a, b) {
                return c.swap(a, {
                    display: "inline-block"
                },
                function() {
                    if (b) return z(a, "marginRight")
                })
            }
        }); ! c.support.pixelPosition && c.fn.position && c.each(["top", "left"],
        function(a, b) {
            c.cssHooks[b] = {
                get: function(a, e) {
                    if (e) {
                        var f = z(a, b);
                        return oa.test(f) ? c(a).position()[b] + "px": f
                    }
                }
            }
        })
    });
    c.expr && c.expr.filters && (c.expr.filters.hidden = function(a) {
        return 0 === a.offsetWidth && 0 === a.offsetHeight || !c.support.reliableHiddenOffsets && "none" === (a.style && a.style.display || z(a, "display"))
    },
    c.expr.filters.visible = function(a) {
        return ! c.expr.filters.hidden(a)
    });
    c.each({
        margin: "",
        padding: "",
        border: "Width"
    },
    function(a, b) {
        c.cssHooks[a + b] = {
            expand: function(c) {
                var e = "string" === typeof c ? c.split(" ") : [c],
                f = {};
                for (c = 0; 4 > c; c++) f[a + O[c] + b] = e[c] || e[c - 2] || e[0];
                return f
            }
        };
        nb.test(a) || (c.cssHooks[a + b].set = I)
    });
    var sc = /%20/g,
    Bb = /\[\]$/,
    pb = /\r?\n/g,
    tc = /^(?:color|date|datetime|datetime-local|email|hidden|month|number|password|range|search|tel|text|time|url|week)$/i,
    uc = /^(?:select|textarea)/i;
    c.fn.extend({
        serialize: function() {
            return c.param(this.serializeArray())
        },
        serializeArray: function() {
            return this.map(function() {
                return this.elements ? c.makeArray(this.elements) : this
            }).filter(function() {
                return this.name && !this.disabled && (this.checked || uc.test(this.nodeName) || tc.test(this.type))
            }).map(function(a, b) {
                var d = c(this).val();
                return null == d ? null: c.isArray(d) ? c.map(d,
                function(a, c) {
                    return {
                        name: b.name,
                        value: a.replace(pb, "\r\n")
                    }
                }) : {
                    name: b.name,
                    value: d.replace(pb, "\r\n")
                }
            }).get()
        }
    });
    c.param = function(a, b) {
        var d, e = [],
        f = function(a, b) {
            b = c.isFunction(b) ? b() : null == b ? "": b;
            e[e.length] = encodeURIComponent(a) + "=" + encodeURIComponent(b)
        };
        b === n && (b = c.ajaxSettings && c.ajaxSettings.traditional);
        if (c.isArray(a) || a.jquery && !c.isPlainObject(a)) c.each(a,
        function() {
            f(this.name, this.value)
        });
        else for (d in a) ia(d, a[d], b, f);
        return e.join("&").replace(sc, "+")
    };
    var Z, W, vc = /#.*$/,
    wc = /^(.*?):[ \t]*([^\r\n]*)\r?$/mg,
    xc = /^(?:GET|HEAD)$/,
    yc = /^\/\//,
    qb = /\?/,
    zc = /<script\b[^<]*(?:(?!<\/script>)<[^<]*)*<\/script>/gi,
    Ac = /([?&])_=[^&]*/,
    rb = /^([\w\+\.\-]+:)(?:\/\/([^\/?#:]*)(?::(\d+)|)|)/,
    sb = c.fn.load,
    za = {},
    tb = {},
    ub = ["*/"] + ["*"];
    try {
        W = Fb.href
    } catch(Hc) {
        W = u.createElement("a"),
        W.href = "",
        W = W.href
    }
    Z = rb.exec(W.toLowerCase()) || [];
    c.fn.load = function(a, b, d) {
        if ("string" !== typeof a && sb) return sb.apply(this, arguments);
        if (!this.length) return this;
        var e, f, g, h = this,
        m = a.indexOf(" ");
        0 <= m && (e = a.slice(m, a.length), a = a.slice(0, m));
        c.isFunction(b) ? (d = b, b = n) : b && "object" === typeof b && (f = "POST");
        c.ajax({
            url: a,
            type: f,
            dataType: "html",
            data: b,
            complete: function(a, b) {
                d && h.each(d, g || [a.responseText, b, a])
            }
        }).done(function(a) {
            g = arguments;
            h.html(e ? c("<div>").append(a.replace(zc, "")).find(e) : a)
        });
        return this
    };
    c.each("ajaxStart ajaxStop ajaxComplete ajaxError ajaxSuccess ajaxSend".split(" "),
    function(a, b) {
        c.fn[b] = function(a) {
            return this.on(b, a)
        }
    });
    c.each(["get", "post"],
    function(a, b) {
        c[b] = function(a, e, f, g) {
            c.isFunction(e) && (g = g || f, f = e, e = n);
            return c.ajax({
                type: b,
                url: a,
                data: e,
                success: f,
                dataType: g
            })
        }
    });
    c.extend({
        getScript: function(a, b) {
            return c.get(a, n, b, "script")
        },
        getJSON: function(a, b, d) {
            return c.get(a, b, d, "json")
        },
        ajaxSetup: function(a, b) {
            b ? Na(a, c.ajaxSettings) : (b = a, a = c.ajaxSettings);
            Na(a, b);
            return a
        },
        ajaxSettings: {
            url: W,
            isLocal: /^(?:about|app|app\-storage|.+\-extension|file|res|widget):$/.test(Z[1]),
            global: !0,
            type: "GET",
            contentType: "application/x-www-form-urlencoded; charset=UTF-8",
            processData: !0,
            async: !0,
            accepts: {
                xml: "application/xml, text/xml",
                html: "text/html",
                text: "text/plain",
                json: "application/json, text/javascript",
                "*": ub
            },
            contents: {
                xml: /xml/,
                html: /html/,
                json: /json/
            },
            responseFields: {
                xml: "responseXML",
                text: "responseText"
            },
            converters: {
                "* text": f.String,
                "text html": !0,
                "text json": c.parseJSON,
                "text xml": c.parseXML
            },
            flatOptions: {
                context: !0,
                url: !0
            }
        },
        ajaxPrefilter: Ma(za),
        ajaxTransport: Ma(tb),
        ajax: function(a, b) {
            function d(a, b, d, g) {
                var k, p, s, L, w = b;
                if (2 !== I) {
                    I = 2;
                    m && clearTimeout(m);
                    h = n;
                    f = g || "";
                    x.readyState = 0 < a ? 4 : 0;
                    if (d) {
                        L = t;
                        g = x;
                        var z, C, R, M, E = L.contents,
                        D = L.dataTypes,
                        K = L.responseFields;
                        for (C in K) C in d && (g[K[C]] = d[C]);
                        for (;
                        "*" === D[0];) D.shift(),
                        z === n && (z = L.mimeType || g.getResponseHeader("content-type"));
                        if (z) for (C in E) if (E[C] && E[C].test(z)) {
                            D.unshift(C);
                            break
                        }
                        if (D[0] in d) R = D[0];
                        else {
                            for (C in d) {
                                if (!D[0] || L.converters[C + " " + D[0]]) {
                                    R = C;
                                    break
                                }
                                M || (M = C)
                            }
                            R = R || M
                        }
                        R ? (R !== D[0] && D.unshift(R), L = d[R]) : L = void 0
                    }
                    if (200 <= a && 300 > a || 304 === a) if (t.ifModified && ((d = x.getResponseHeader("Last-Modified")) && (c.lastModified[e] = d), (d = x.getResponseHeader("Etag")) && (c.etag[e] = d)), 304 === a) w = "notmodified",
                    k = !0;
                    else {
                        a: {
                            p = t;
                            s = L;
                            var B, G, w = p.dataTypes.slice();
                            z = w[0];
                            C = {};
                            R = 0;
                            p.dataFilter && (s = p.dataFilter(s, p.dataType));
                            if (w[1]) for (B in p.converters) C[B.toLowerCase()] = p.converters[B];
                            for (; d = w[++R];) if ("*" !== d) {
                                if ("*" !== z && z !== d) {
                                    B = C[z + " " + d] || C["* " + d];
                                    if (!B) for (G in C) if (k = G.split(" "), k[1] === d && (B = C[z + " " + k[0]] || C["* " + k[0]])) { ! 0 === B ? B = C[G] : !0 !== C[G] && (d = k[0], w.splice(R--, 0, d));
                                        break
                                    }
                                    if (!0 !== B) if (B && p["throws"]) s = B(s);
                                    else try {
                                        s = B(s)
                                    } catch(J) {
                                        k = {
                                            state: "parsererror",
                                            error: B ? J: "No conversion from " + z + " to " + d
                                        };
                                        break a
                                    }
                                }
                                z = d
                            }
                            k = {
                                state: "success",
                                data: s
                            }
                        }
                        w = k.state;
                        p = k.data;
                        s = k.error;
                        k = !s
                    } else if (s = w, !w || a) w = "error",
                    0 > a && (a = 0);
                    x.status = a;
                    x.statusText = (b || w) + "";
                    k ? v.resolveWith(u, [p, w, x]) : v.rejectWith(u, [x, w, s]);
                    x.statusCode(F);
                    F = n;
                    r && y.trigger("ajax" + (k ? "Success": "Error"), [x, t, k ? p: s]);
                    N.fireWith(u, [x, w]);
                    r && (y.trigger("ajaxComplete", [x, t]), --c.active || c.event.trigger("ajaxStop"))
                }
            }
            "object" === typeof a && (b = a, a = n);
            b = b || {};
            var e, f, g, h, m, p, r, s, t = c.ajaxSetup({},
            b),
            u = t.context || t,
            y = u !== t && (u.nodeType || u instanceof c) ? c(u) : c.event,
            v = c.Deferred(),
            N = c.Callbacks("once memory"),
            F = t.statusCode || {},
            z = {},
            w = {},
            I = 0,
            M = "canceled",
            x = {
                readyState: 0,
                setRequestHeader: function(a, b) {
                    if (!I) {
                        var c = a.toLowerCase();
                        a = w[c] = w[c] || a;
                        z[a] = b
                    }
                    return this
                },
                getAllResponseHeaders: function() {
                    return 2 === I ? f: null
                },
                getResponseHeader: function(a) {
                    var b;
                    if (2 === I) {
                        if (!g) for (g = {}; b = wc.exec(f);) g[b[1].toLowerCase()] = b[2];
                        b = g[a.toLowerCase()]
                    }
                    return b === n ? null: b
                },
                overrideMimeType: function(a) {
                    I || (t.mimeType = a);
                    return this
                },
                abort: function(a) {
                    a = a || M;
                    h && h.abort(a);
                    d(0, a);
                    return this
                }
            };
            v.promise(x);
            x.success = x.done;
            x.error = x.fail;
            x.complete = N.add;
            x.statusCode = function(a) {
                if (a) {
                    var b;
                    if (2 > I) for (b in a) F[b] = [F[b], a[b]];
                    else b = a[x.status],
                    x.always(b)
                }
                return this
            };
            t.url = ((a || t.url) + "").replace(vc, "").replace(yc, Z[1] + "//");
            t.dataTypes = c.trim(t.dataType || "*").toLowerCase().split(X);
            null == t.crossDomain && (p = rb.exec(t.url.toLowerCase()), t.crossDomain = !(!p || !(p[1] !== Z[1] || p[2] !== Z[2] || (p[3] || ("http:" === p[1] ? 80 : 443)) != (Z[3] || ("http:" === Z[1] ? 80 : 443)))));
            t.data && (t.processData && "string" !== typeof t.data) && (t.data = c.param(t.data, t.traditional));
            Y(za, t, b, x);
            if (2 === I) return x;
            r = t.global;
            t.type = t.type.toUpperCase();
            t.hasContent = !xc.test(t.type);
            r && 0 === c.active++&&c.event.trigger("ajaxStart");
            if (!t.hasContent && (t.data && (t.url += (qb.test(t.url) ? "&": "?") + t.data, delete t.data), e = t.url, !1 === t.cache)) {
                p = c.now();
                var E = t.url.replace(Ac, "$1_=" + p);
                t.url = E + (E === t.url ? (qb.test(t.url) ? "&": "?") + "_=" + p: "")
            } (t.data && t.hasContent && !1 !== t.contentType || b.contentType) && x.setRequestHeader("Content-Type", t.contentType);
            t.ifModified && (e = e || t.url, c.lastModified[e] && x.setRequestHeader("If-Modified-Since", c.lastModified[e]), c.etag[e] && x.setRequestHeader("If-None-Match", c.etag[e]));
            x.setRequestHeader("Accept", t.dataTypes[0] && t.accepts[t.dataTypes[0]] ? t.accepts[t.dataTypes[0]] + ("*" !== t.dataTypes[0] ? ", " + ub + "; q=0.01": "") : t.accepts["*"]);
            for (s in t.headers) x.setRequestHeader(s, t.headers[s]);
            if (t.beforeSend && (!1 === t.beforeSend.call(u, x, t) || 2 === I)) return x.abort();
            M = "abort";
            for (s in {
                success: 1,
                error: 1,
                complete: 1
            }) x[s](t[s]);
            if (h = Y(tb, t, b, x)) {
                x.readyState = 1;
                r && y.trigger("ajaxSend", [x, t]);
                t.async && 0 < t.timeout && (m = setTimeout(function() {
                    x.abort("timeout")
                },
                t.timeout));
                try {
                    I = 1,
                    h.send(z, d)
                } catch(D) {
                    if (2 > I) d( - 1, D);
                    else throw D;
                }
            } else d( - 1, "No Transport");
            return x
        },
        active: 0,
        lastModified: {},
        etag: {}
    });
    var vb = [],
    Bc = /\?/,
    wa = /(=)\?(?=&|$)|\?\?/,
    Cc = c.now();
    c.ajaxSetup({
        jsonp: "callback",
        jsonpCallback: function() {
            var a = vb.pop() || c.expando + "_" + Cc++;
            this[a] = !0;
            return a
        }
    });
    c.ajaxPrefilter("json jsonp",
    function(a, b, d) {
        var e, g, k, h = a.data,
        m = a.url,
        p = !1 !== a.jsonp,
        r = p && wa.test(m),
        s = p && !r && "string" === typeof h && !(a.contentType || "").indexOf("application/x-www-form-urlencoded") && wa.test(h);
        if ("jsonp" === a.dataTypes[0] || r || s) return e = a.jsonpCallback = c.isFunction(a.jsonpCallback) ? a.jsonpCallback() : a.jsonpCallback,
        g = f[e],
        r ? a.url = m.replace(wa, "$1" + e) : s ? a.data = h.replace(wa, "$1" + e) : p && (a.url += (Bc.test(m) ? "&": "?") + a.jsonp + "=" + e),
        a.converters["script json"] = function() {
            k || c.error(e + " was not called");
            return k[0]
        },
        a.dataTypes[0] = "json",
        f[e] = function() {
            k = arguments
        },
        d.always(function() {
            f[e] = g;
            a[e] && (a.jsonpCallback = b.jsonpCallback, vb.push(e));
            k && c.isFunction(g) && g(k[0]);
            k = g = n
        }),
        "script"
    });
    c.ajaxSetup({
        accepts: {
            script: "text/javascript, application/javascript, application/ecmascript, application/x-ecmascript"
        },
        contents: {
            script: /javascript|ecmascript/
        },
        converters: {
            "text script": function(a) {
                c.globalEval(a);
                return a
            }
        }
    });
    c.ajaxPrefilter("script",
    function(a) {
        a.cache === n && (a.cache = !1);
        a.crossDomain && (a.type = "GET", a.global = !1)
    });
    c.ajaxTransport("script",
    function(a) {
        if (a.crossDomain) {
            var b, c = u.head || u.getElementsByTagName("head")[0] || u.documentElement;
            return {
                send: function(e, f) {
                    b = u.createElement("script");
                    b.async = "async";
                    a.scriptCharset && (b.charset = a.scriptCharset);
                    b.src = a.url;
                    b.onload = b.onreadystatechange = function(a, e) {
                        if (e || !b.readyState || /loaded|complete/.test(b.readyState)) b.onload = b.onreadystatechange = null,
                        c && b.parentNode && c.removeChild(b),
                        b = n,
                        e || f(200, "success")
                    };
                    c.insertBefore(b, c.firstChild)
                },
                abort: function() {
                    if (b) b.onload(0, 1)
                }
            }
        }
    });
    var ha, Ha = f.ActiveXObject ?
    function() {
        for (var a in ha) ha[a](0, 1)
    }: !1,
    Dc = 0;
    c.ajaxSettings.xhr = f.ActiveXObject ?
    function() {
        var a;
        if (! (a = !this.isLocal && Oa())) a: {
            try {
                a = new f.ActiveXObject("Microsoft.XMLHTTP");
                break a
            } catch(b) {}
            a = void 0
        }
        return a
    }: Oa; (function(a) {
        c.extend(c.support, {
            ajax: !!a,
            cors: !!a && "withCredentials" in a
        })
    })(c.ajaxSettings.xhr());
    c.support.ajax && c.ajaxTransport(function(a) {
        if (!a.crossDomain || c.support.cors) {
            var b;
            return {
                send: function(d, e) {
                    var g, k, h = a.xhr();
                    a.username ? h.open(a.type, a.url, a.async, a.username, a.password) : h.open(a.type, a.url, a.async);
                    if (a.xhrFields) for (k in a.xhrFields) h[k] = a.xhrFields[k];
                    a.mimeType && h.overrideMimeType && h.overrideMimeType(a.mimeType); ! a.crossDomain && !d["X-Requested-With"] && (d["X-Requested-With"] = "XMLHttpRequest");
                    try {
                        for (k in d) h.setRequestHeader(k, d[k])
                    } catch(m) {}
                    h.send(a.hasContent && a.data || null);
                    b = function(d, f) {
                        var k, m, p, r, s;
                        try {
                            if (b && (f || 4 === h.readyState)) if (b = n, g && (h.onreadystatechange = c.noop, Ha && delete ha[g]), f) 4 !== h.readyState && h.abort();
                            else {
                                k = h.status;
                                p = h.getAllResponseHeaders();
                                r = {};
                                if ((s = h.responseXML) && s.documentElement) r.xml = s;
                                try {
                                    r.text = h.responseText
                                } catch(u) {}
                                try {
                                    m = h.statusText
                                } catch(y) {
                                    m = ""
                                } ! k && a.isLocal && !a.crossDomain ? k = r.text ? 200 : 404 : 1223 === k && (k = 204)
                            }
                        } catch(v) {
                            f || e( - 1, v)
                        }
                        r && e(k, m, r, p)
                    };
                    a.async ? 4 === h.readyState ? setTimeout(b, 0) : (g = ++Dc, Ha && (ha || (ha = {},
                    c(f).unload(Ha)), ha[g] = b), h.onreadystatechange = b) : b()
                },
                abort: function() {
                    b && b(0, 1)
                }
            }
        }
    });
    var fa, xa, Ec = /^(?:toggle|show|hide)$/,
    Fc = RegExp("^(?:([-+])=|)(" + sa + ")([a-z%]*)$", "i"),
    Gc = /queueHooks$/,
    pa = [function(a, b, d) {
        var e, f, g, h, m, p, r = this,
        n = a.style,
        s = {},
        u = [],
        y = a.nodeType && Q(a);
        d.queue || (m = c._queueHooks(a, "fx"), null == m.unqueued && (m.unqueued = 0, p = m.empty.fire, m.empty.fire = function() {
            m.unqueued || p()
        }), m.unqueued++, r.always(function() {
            r.always(function() {
                m.unqueued--;
                c.queue(a, "fx").length || m.empty.fire()
            })
        }));
        if (1 === a.nodeType && ("height" in b || "width" in b)) d.overflow = [n.overflow, n.overflowX, n.overflowY],
        "inline" === c.css(a, "display") && "none" === c.css(a, "float") && (!c.support.inlineBlockNeedsLayout || "inline" === ea(a.nodeName) ? n.display = "inline-block": n.zoom = 1);
        d.overflow && (n.overflow = "hidden", c.support.shrinkWrapBlocks || r.done(function() {
            n.overflow = d.overflow[0];
            n.overflowX = d.overflow[1];
            n.overflowY = d.overflow[2]
        }));
        for (e in b) g = b[e],
        Ec.exec(g) && (delete b[e], f = f || "toggle" === g, g !== (y ? "hide": "show") && u.push(e));
        if (b = u.length) {
            g = c._data(a, "fxshow") || c._data(a, "fxshow", {});
            "hidden" in g && (y = g.hidden);
            f && (g.hidden = !y);
            y ? c(a).show() : r.done(function() {
                c(a).hide()
            });
            r.done(function() {
                var b;
                c.removeData(a, "fxshow", !0);
                for (b in s) c.style(a, b, s[b])
            });
            for (e = 0; e < b; e++) f = u[e],
            h = r.createTween(f, y ? g[f] : 0),
            s[f] = g[f] || c.style(a, f),
            f in g || (g[f] = h.start, y && (h.end = h.start, h.start = "width" === f || "height" === f ? 1 : 0))
        }
    }],
    ja = {
        "*": [function(a, b) {
            var d, e, f = this.createTween(a, b),
            g = Fc.exec(b),
            h = f.cur(),
            m = +h || 0,
            p = 1,
            r = 20;
            if (g) {
                d = +g[2];
                e = g[3] || (c.cssNumber[a] ? "": "px");
                if ("px" !== e && m) {
                    m = c.css(f.elem, a, !0) || d || 1;
                    do p = p || ".5",
                    m /= p,
                    c.style(f.elem, a, m + e);
                    while (p !== (p = f.cur() / h) && 1 !== p && --r)
                }
                f.unit = e;
                f.start = m;
                f.end = g[1] ? m + (g[1] + 1) * d: d
            }
            return f
        }]
    };
    c.Animation = c.extend(Qa, {
        tweener: function(a, b) {
            c.isFunction(a) ? (b = a, a = ["*"]) : a = a.split(" ");
            for (var d, e = 0,
            f = a.length; e < f; e++) d = a[e],
            ja[d] = ja[d] || [],
            ja[d].unshift(b)
        },
        prefilter: function(a, b) {
            b ? pa.unshift(a) : pa.push(a)
        }
    });
    c.Tween = E;
    E.prototype = {
        constructor: E,
        init: function(a, b, d, e, f, g) {
            this.elem = a;
            this.prop = d;
            this.easing = f || "swing";
            this.options = b;
            this.start = this.now = this.cur();
            this.end = e;
            this.unit = g || (c.cssNumber[d] ? "": "px")
        },
        cur: function() {
            var a = E.propHooks[this.prop];
            return a && a.get ? a.get(this) : E.propHooks._default.get(this)
        },
        run: function(a) {
            var b, d = E.propHooks[this.prop];
            this.pos = this.options.duration ? b = c.easing[this.easing](a, this.options.duration * a, 0, 1, this.options.duration) : b = a;
            this.now = (this.end - this.start) * b + this.start;
            this.options.step && this.options.step.call(this.elem, this.now, this);
            d && d.set ? d.set(this) : E.propHooks._default.set(this);
            return this
        }
    };
    E.prototype.init.prototype = E.prototype;
    E.propHooks = {
        _default: {
            get: function(a) {
                if (null != a.elem[a.prop] && (!a.elem.style || null == a.elem.style[a.prop])) return a.elem[a.prop];
                a = c.css(a.elem, a.prop, !1, "");
                return ! a || "auto" === a ? 0 : a
            },
            set: function(a) {
                if (c.fx.step[a.prop]) c.fx.step[a.prop](a);
                else a.elem.style && (null != a.elem.style[c.cssProps[a.prop]] || c.cssHooks[a.prop]) ? c.style(a.elem, a.prop, a.now + a.unit) : a.elem[a.prop] = a.now
            }
        }
    };
    E.propHooks.scrollTop = E.propHooks.scrollLeft = {
        set: function(a) {
            a.elem.nodeType && a.elem.parentNode && (a.elem[a.prop] = a.now)
        }
    };
    c.each(["toggle", "show", "hide"],
    function(a, b) {
        var d = c.fn[b];
        c.fn[b] = function(e, f, g) {
            return null == e || "boolean" === typeof e || !a && c.isFunction(e) && c.isFunction(f) ? d.apply(this, arguments) : this.animate(qa(b, !0), e, f, g)
        }
    });
    c.fn.extend({
        fadeTo: function(a, b, c, e) {
            return this.filter(Q).css("opacity", 0).show().end().animate({
                opacity: b
            },
            a, c, e)
        },
        animate: function(a, b, d, e) {
            var f = c.isEmptyObject(a),
            g = c.speed(b, d, e);
            b = function() {
                var b = Qa(this, c.extend({},
                a), g);
                f && b.stop(!0)
            };
            return f || !1 === g.queue ? this.each(b) : this.queue(g.queue, b)
        },
        stop: function(a, b, d) {
            var e = function(a) {
                var b = a.stop;
                delete a.stop;
                b(d)
            };
            "string" !== typeof a && (d = b, b = a, a = n);
            b && !1 !== a && this.queue(a || "fx", []);
            return this.each(function() {
                var b = !0,
                f = null != a && a + "queueHooks",
                g = c.timers,
                h = c._data(this);
                if (f) h[f] && h[f].stop && e(h[f]);
                else for (f in h) h[f] && (h[f].stop && Gc.test(f)) && e(h[f]);
                for (f = g.length; f--;) if (g[f].elem === this && (null == a || g[f].queue === a)) g[f].anim.stop(d),
                b = !1,
                g.splice(f, 1); (b || !d) && c.dequeue(this, a)
            })
        }
    });
    c.each({
        slideDown: qa("show"),
        slideUp: qa("hide"),
        slideToggle: qa("toggle"),
        fadeIn: {
            opacity: "show"
        },
        fadeOut: {
            opacity: "hide"
        },
        fadeToggle: {
            opacity: "toggle"
        }
    },
    function(a, b) {
        c.fn[a] = function(a, c, e) {
            return this.animate(b, a, c, e)
        }
    });
    c.speed = function(a, b, d) {
        var e = a && "object" === typeof a ? c.extend({},
        a) : {
            complete: d || !d && b || c.isFunction(a) && a,
            duration: a,
            easing: d && b || b && !c.isFunction(b) && b
        };
        e.duration = c.fx.off ? 0 : "number" === typeof e.duration ? e.duration: e.duration in c.fx.speeds ? c.fx.speeds[e.duration] : c.fx.speeds._default;
        if (null == e.queue || !0 === e.queue) e.queue = "fx";
        e.old = e.complete;
        e.complete = function() {
            c.isFunction(e.old) && e.old.call(this);
            e.queue && c.dequeue(this, e.queue)
        };
        return e
    };
    c.easing = {
        linear: function(a) {
            return a
        },
        swing: function(a) {
            return 0.5 - Math.cos(a * Math.PI) / 2
        }
    };
    c.timers = [];
    c.fx = E.prototype.init;
    c.fx.tick = function() {
        var a, b = c.timers,
        d = 0;
        for (fa = c.now(); d < b.length; d++) a = b[d],
        !a() && b[d] === a && b.splice(d--, 1);
        b.length || c.fx.stop();
        fa = n
    };
    c.fx.timer = function(a) {
        a() && (c.timers.push(a) && !xa) && (xa = setInterval(c.fx.tick, c.fx.interval))
    };
    c.fx.interval = 13;
    c.fx.stop = function() {
        clearInterval(xa);
        xa = null
    };
    c.fx.speeds = {
        slow: 600,
        fast: 200,
        _default: 400
    };
    c.fx.step = {};
    c.expr && c.expr.filters && (c.expr.filters.animated = function(a) {
        return c.grep(c.timers,
        function(b) {
            return a === b.elem
        }).length
    });
    var wb = /^(?:body|html)$/i;
    c.fn.offset = function(a) {
        if (arguments.length) return a === n ? this: this.each(function(b) {
            c.offset.setOffset(this, a, b)
        });
        var b, d, e, f, g, h = {
            top: 0,
            left: 0
        };
        if (e = (f = this[0]) && f.ownerDocument) {
            if ((d = e.body) === f) return c.offset.bodyOffset(f);
            b = e.documentElement;
            if (!c.contains(b, f)) return h;
            "undefined" !== typeof f.getBoundingClientRect && (h = f.getBoundingClientRect());
            e = Ra(e);
            f = b.clientTop || d.clientTop || 0;
            d = b.clientLeft || d.clientLeft || 0;
            g = e.pageYOffset || b.scrollTop;
            b = e.pageXOffset || b.scrollLeft;
            return {
                top: h.top + g - f,
                left: h.left + b - d
            }
        }
    };
    c.offset = {
        bodyOffset: function(a) {
            var b = a.offsetTop,
            d = a.offsetLeft;
            c.support.doesNotIncludeMarginInBodyOffset && (b += parseFloat(c.css(a, "marginTop")) || 0, d += parseFloat(c.css(a, "marginLeft")) || 0);
            return {
                top: b,
                left: d
            }
        },
        setOffset: function(a, b, d) {
            var e = c.css(a, "position");
            "static" === e && (a.style.position = "relative");
            var f = c(a),
            g = f.offset(),
            h = c.css(a, "top"),
            m = c.css(a, "left"),
            p = {},
            r = {}; ("absolute" === e || "fixed" === e) && -1 < c.inArray("auto", [h, m]) ? (r = f.position(), e = r.top, m = r.left) : (e = parseFloat(h) || 0, m = parseFloat(m) || 0);
            c.isFunction(b) && (b = b.call(a, d, g));
            null != b.top && (p.top = b.top - g.top + e);
            null != b.left && (p.left = b.left - g.left + m);
            "using" in b ? b.using.call(a, p) : f.css(p)
        }
    };
    c.fn.extend({
        position: function() {
            if (this[0]) {
                var a = this[0],
                b = this.offsetParent(),
                d = this.offset(),
                e = wb.test(b[0].nodeName) ? {
                    top: 0,
                    left: 0
                }: b.offset();
                d.top -= parseFloat(c.css(a, "marginTop")) || 0;
                d.left -= parseFloat(c.css(a, "marginLeft")) || 0;
                e.top += parseFloat(c.css(b[0], "borderTopWidth")) || 0;
                e.left += parseFloat(c.css(b[0], "borderLeftWidth")) || 0;
                return {
                    top: d.top - e.top,
                    left: d.left - e.left
                }
            }
        },
        offsetParent: function() {
            return this.map(function() {
                for (var a = this.offsetParent || u.body; a && !wb.test(a.nodeName) && "static" === c.css(a, "position");) a = a.offsetParent;
                return a || u.body
            })
        }
    });
    c.each({
        scrollLeft: "pageXOffset",
        scrollTop: "pageYOffset"
    },
    function(a, b) {
        var d = /Y/.test(b);
        c.fn[a] = function(e) {
            return c.access(this,
            function(a, e, f) {
                var g = Ra(a);
                if (f === n) return g ? b in g ? g[b] : g.document.documentElement[e] : a[e];
                g ? g.scrollTo(!d ? f: c(g).scrollLeft(), d ? f: c(g).scrollTop()) : a[e] = f
            },
            a, e, arguments.length, null)
        }
    });
    c.each({
        Height: "height",
        Width: "width"
    },
    function(a, b) {
        c.each({
            padding: "inner" + a,
            content: b,
            "": "outer" + a
        },
        function(d, e) {
            c.fn[e] = function(e, f) {
                var g = arguments.length && (d || "boolean" !== typeof e),
                h = d || (!0 === e || !0 === f ? "margin": "border");
                return c.access(this,
                function(b, d, e) {
                    return c.isWindow(b) ? b.document.documentElement["client" + a] : 9 === b.nodeType ? (d = b.documentElement, Math.max(b.body["scroll" + a], d["scroll" + a], b.body["offset" + a], d["offset" + a], d["client" + a])) : e === n ? c.css(b, d, e, h) : c.style(b, d, e, h)
                },
                b, g ? e: n, g, null)
            }
        })
    });
    f.jQuery = f.$ = c;
    "function" === typeof define && (define.amd && define.amd.jQuery) && define("jquery", [],
    function() {
        return c
    })
})(window); (function(f) {
    f.backstretch = function(n, e, g) {
        function h() {
            if (n) {
                var e;
                0 == s.length ? s = f("<div />").attr("id", "backstretch").css({
                    left: 0,
                    top: 0,
                    position: y ? "fixed": "absolute",
                    overflow: "hidden",
                    zIndex: -999999,
                    margin: 0,
                    padding: 0,
                    height: "100%",
                    width: "100%"
                }) : s.find("img").addClass("deleteable");
                e = f("<img />").css({
                    position: "absolute",
                    display: "none",
                    margin: 0,
                    padding: 0,
                    border: "none",
                    zIndex: -999999,
                    maxWidth: "none"
                }).bind("load",
                function(e) {
                    var h = f(this),
                    r;
                    h.css({
                        width: "auto",
                        height: "auto"
                    });
                    r = this.width || f(e.target).width();
                    e = this.height || f(e.target).height();
                    F = r / e;
                    p();
                    h.fadeIn(v.speed,
                    function() {
                        s.find(".deleteable").remove();
                        "function" == typeof g && g()
                    })
                }).appendTo(s);
                0 == f("body #backstretch").length && (0 === f(window).scrollTop() && window.scrollTo(0, 0), f("body").append(s));
                s.data("settings", v);
                e.attr("src", n);
                f(window).unbind("resize.backstretch").bind("resize.backstretch",
                function() {
                    "onorientationchange" in window && 0 === window.pageYOffset && window.scrollTo(0, 1);
                    p()
                })
            }
        }
        function p() {
            try {
                Q = {
                    left: 0,
                    top: 0
                },
                rootWidth = D = G.width(),
                rootHeight = N ? window.innerHeight: G.height(),
                K = D / F,
                K >= rootHeight ? (J = (K - rootHeight) / 2, v.centeredY && (Q.top = "-" + J + "px")) : (K = rootHeight, D = K * F, J = (D - rootWidth) / 2, v.centeredX && (Q.left = "-" + J + "px")),
                s.css({
                    width: rootWidth,
                    height: rootHeight
                }).find("img:not(.deleteable)").css({
                    width: D,
                    height: K
                }).css(Q)
            } catch(e) {}
        }
        var r = {
            centeredX: !0,
            centeredY: !0,
            speed: 0
        },
        s = f("#backstretch"),
        v = s.data("settings") || r;
        s.data("settings");
        var G, y, N, F, D, K, J, Q;
        e && "object" == typeof e && f.extend(v, e);
        e && "function" == typeof e && (g = e);
        f(document).ready(function() {
            var e = window,
            g = navigator.userAgent,
            p = navigator.platform,
            r = g.match(/AppleWebKit\/([0-9]+)/),
            r = !!r && r[1],
            n = g.match(/Fennec\/([0-9]+)/),
            n = !!n && n[1],
            s = g.match(/Opera Mobi\/([0-9]+)/),
            v = !!s && s[1],
            F = g.match(/MSIE ([0-9]+)/),
            F = !!F && F[1];
            G = (y = !(( - 1 < p.indexOf("iPhone") || -1 < p.indexOf("iPad") || -1 < p.indexOf("iPod")) && r && 534 > r || e.operamini && "[object OperaMini]" === {}.toString.call(e.operamini) || s && 7458 > v || -1 < g.indexOf("Android") && r && 533 > r || n && 6 > n || "palmGetResource" in window && r && 534 > r || -1 < g.indexOf("MeeGo") && -1 < g.indexOf("NokiaBrowser/8.5.0") || F && 6 >= F)) ? f(window) : f(document);
            N = y && window.innerHeight;
            h()
        });
        return this
    }
})(jQuery); (function(f, n, e) {
    function g(f) {
        var g = {},
        h = /^jQuery\d+$/;
        e.each(f.attributes,
        function(e, f) {
            f.specified && !h.test(f.name) && (g[f.name] = f.value)
        });
        return g
    }
    function h(f, g) {
        var h = e(this);
        if (this.value == h.attr("placeholder") && h.hasClass("placeholder")) if (h.data("placeholder-password")) {
            h = h.hide().next().show().attr("id", h.removeAttr("id").data("placeholder-id"));
            if (!0 === f) return h[0].value = g;
            h.focus()
        } else this.value = "",
        h.removeClass("placeholder"),
        this == n.activeElement && this.select()
    }
    function p() {
        var f, p = e(this),
        r = this.id;
        if ("" == this.value) {
            if ("password" == this.type) {
                if (!p.data("placeholder-textinput")) {
                    try {
                        f = p.clone().attr({
                            type: "text"
                        })
                    } catch(n) {
                        f = e("<input>").attr(e.extend(g(this), {
                            type: "text"
                        }))
                    }
                    f.removeAttr("name").addClass("password").data({
                        "placeholder-password": !0,
                        "placeholder-id": r
                    }).bind("focus.placeholder", h);
                    p.data({
                        "placeholder-textinput": f,
                        "placeholder-id": r
                    }).before(f)
                }
                p = p.removeAttr("id").hide().prev().attr("id", r).show()
            }
            p.addClass("placeholder");
            p[0].value = p.attr("placeholder")
        } else p.removeClass("placeholder")
    }
    var r = "placeholder" in n.createElement("input"),
    s = "placeholder" in n.createElement("textarea"),
    v = e.fn,
    G = e.valHooks;
    r && s ? (v = v.placeholder = function() {
        return this
    },
    v.input = v.textarea = !0) : (v = v.placeholder = function() {
        this.filter((r ? "textarea": ":input") + "[placeholder]").not(".placeholder").bind({
            "focus.placeholder": h,
            "blur.placeholder": p
        }).data("placeholder-enabled", !0).trigger("blur.placeholder");
        return this
    },
    v.input = r, v.textarea = s, v = {
        get: function(f) {
            var g = e(f);
            return g.data("placeholder-enabled") && g.hasClass("placeholder") ? "": f.value
        },
        set: function(f, g) {
            var r = e(f);
            if (!r.data("placeholder-enabled")) return f.value = g;
            "" == g ? (f.value = g, f != n.activeElement && p.call(f)) : r.hasClass("placeholder") ? h.call(f, !0, g) || (f.value = g) : f.value = g;
            return r
        }
    },
    r || (G.input = v), s || (G.textarea = v), e(function() {
        e(n).delegate("form", "submit.placeholder",
        function() {
            var f = e(".placeholder", this).each(h);
            setTimeout(function() {
                f.each(p)
            },
            10)
        })
    }), e(f).bind("beforeunload.placeholder",
    function() {
        e(".placeholder").each(function() {
            this.value = ""
        })
    }))
})(this, document, jQuery); !
function(f) {
    f(function() {
        var n = f.support,
        e;
        a: {
            e = document.createElement("bootstrap");
            var g = {
                WebkitTransition: "webkitTransitionEnd",
                MozTransition: "transitionend",
                OTransition: "oTransitionEnd otransitionend",
                transition: "transitionend"
            },
            h;
            for (h in g) if (void 0 !== e.style[h]) {
                e = g[h];
                break a
            }
            e = void 0
        }
        n.transition = e && {
            end: e
        }
    })
} (window.jQuery); !
function(f) {
    var n = function(e) {
        f(e).on("click", '[data-dismiss="alert"]', this.close)
    };
    n.prototype.close = function(e) {
        function g() {
            r.trigger("closed").remove()
        }
        var h = f(this),
        p = h.attr("data-target"),
        r;
        p || (p = h.attr("href"), p = p && p.replace(/.*(?=#[^\s]*$)/, ""));
        r = f(p);
        e && e.preventDefault();
        r.length || (r = h.hasClass("alert") ? h: h.parent());
        r.trigger(e = f.Event("close"));
        e.isDefaultPrevented() || (r.removeClass("in"), f.support.transition && r.hasClass("fade") ? r.on(f.support.transition.end, g) : g())
    };
    f.fn.alert = function(e) {
        return this.each(function() {
            var g = f(this),
            h = g.data("alert");
            h || g.data("alert", h = new n(this));
            "string" == typeof e && h[e].call(g)
        })
    };
    f.fn.alert.Constructor = n;
    f(function() {
        f("body").on("click.alert.data-api", '[data-dismiss="alert"]', n.prototype.close)
    })
} (window.jQuery); !
function(f) {
    var n = function(e, g) {
        this.$element = f(e);
        this.options = f.extend({},
        f.fn.button.defaults, g)
    };
    n.prototype.setState = function(e) {
        var f = this.$element,
        h = f.data(),
        p = f.is("input") ? "val": "html";
        e += "Text";
        h.resetText || f.data("resetText", f[p]());
        f[p](h[e] || this.options[e]);
        setTimeout(function() {
            "loadingText" == e ? f.addClass("disabled").attr("disabled", "disabled") : f.removeClass("disabled").removeAttr("disabled")
        },
        0)
    };
    n.prototype.toggle = function() {
        var e = this.$element.parent('[data-toggle="buttons-radio"]');
        e && e.find(".active").removeClass("active");
        this.$element.toggleClass("active")
    };
    f.fn.button = function(e) {
        return this.each(function() {
            var g = f(this),
            h = g.data("button"),
            p = "object" == typeof e && e;
            h || g.data("button", h = new n(this, p));
            "toggle" == e ? h.toggle() : e && h.setState(e)
        })
    };
    f.fn.button.defaults = {
        loadingText: "loading..."
    };
    f.fn.button.Constructor = n;
    f(function() {
        f("body").on("click.button.data-api", "[data-toggle^=button]",
        function(e) {
            e = f(e.target);
            e.hasClass("btn") || (e = e.closest(".btn"));
            e.button("toggle")
        })
    })
} (window.jQuery); !
function(f) {
    var n = function(e, g) {
        this.$element = f(e);
        this.options = g;
        this.options.slide && this.slide(this.options.slide);
        "hover" == this.options.pause && this.$element.on("mouseenter", f.proxy(this.pause, this)).on("mouseleave", f.proxy(this.cycle, this))
    };
    n.prototype = {
        cycle: function(e) {
            return e || (this.paused = !1),
            this.options.interval && !this.paused && (this.interval = setInterval(f.proxy(this.next, this), this.options.interval)),
            this
        },
        to: function(e) {
            var g = this.$element.find(".item.active"),
            h = g.parent().children(),
            g = h.index(g),
            p = this;
            if (! (e > h.length - 1 || 0 > e)) return this.sliding ? this.$element.one("slid",
            function() {
                p.to(e)
            }) : g == e ? this.pause().cycle() : this.slide(e > g ? "next": "prev", f(h[e]))
        },
        pause: function(e) {
            return e || (this.paused = !0),
            this.$element.find(".next, .prev").length && f.support.transition.end && (this.$element.trigger(f.support.transition.end), this.cycle()),
            clearInterval(this.interval),
            this.interval = null,
            this
        },
        next: function() {
            if (!this.sliding) return this.slide("next")
        },
        prev: function() {
            if (!this.sliding) return this.slide("prev")
        },
        slide: function(e, g) {
            var h = this.$element.find(".item.active"),
            p = g || h[e](),
            r = this.interval,
            n = "next" == e ? "left": "right",
            v = "next" == e ? "first": "last",
            G = this,
            y = f.Event("slide", {
                relatedTarget: p[0]
            });
            this.sliding = !0;
            r && this.pause();
            p = p.length ? p: this.$element.find(".item")[v]();
            if (!p.hasClass("active")) {
                if (f.support.transition && this.$element.hasClass("slide")) {
                    this.$element.trigger(y);
                    if (y.isDefaultPrevented()) return;
                    p.addClass(e);
                    p[0].offsetWidth;
                    h.addClass(n);
                    p.addClass(n);
                    this.$element.one(f.support.transition.end,
                    function() {
                        p.removeClass([e, n].join(" ")).addClass("active");
                        h.removeClass(["active", n].join(" "));
                        G.sliding = !1;
                        setTimeout(function() {
                            G.$element.trigger("slid")
                        },
                        0)
                    })
                } else {
                    this.$element.trigger(y);
                    if (y.isDefaultPrevented()) return;
                    h.removeClass("active");
                    p.addClass("active");
                    this.sliding = !1;
                    this.$element.trigger("slid")
                }
                return r && this.cycle(),
                this
            }
        }
    };
    f.fn.carousel = function(e) {
        return this.each(function() {
            var g = f(this),
            h = g.data("carousel"),
            p = f.extend({},
            f.fn.carousel.defaults, "object" == typeof e && e),
            r = "string" == typeof e ? e: p.slide;
            h || g.data("carousel", h = new n(this, p));
            "number" == typeof e ? h.to(e) : r ? h[r]() : p.interval && h.cycle()
        })
    };
    f.fn.carousel.defaults = {
        interval: 5E3,
        pause: "hover"
    };
    f.fn.carousel.Constructor = n;
    f(function() {
        f("body").on("click.carousel.data-api", "[data-slide]",
        function(e) {
            var g = f(this),
            h,
            p = f(g.attr("data-target") || (h = g.attr("href")) && h.replace(/.*(?=#[^\s]+$)/, "")),
            g = !p.data("modal") && f.extend({},
            p.data(), g.data());
            p.carousel(g);
            e.preventDefault()
        })
    })
} (window.jQuery); !
function(f) {
    var n = function(e, g) {
        this.$element = f(e);
        this.options = f.extend({},
        f.fn.collapse.defaults, g);
        this.options.parent && (this.$parent = f(this.options.parent));
        this.options.toggle && this.toggle()
    };
    n.prototype = {
        constructor: n,
        dimension: function() {
            return this.$element.hasClass("width") ? "width": "height"
        },
        show: function() {
            var e, g, h, p;
            if (!this.transitioning) {
                e = this.dimension();
                g = f.camelCase(["scroll", e].join("-"));
                if ((h = this.$parent && this.$parent.find("> .accordion-group > .in")) && h.length) {
                    if ((p = h.data("collapse")) && p.transitioning) return;
                    h.collapse("hide");
                    p || h.data("collapse", null)
                }
                this.$element[e](0);
                this.transition("addClass", f.Event("show"), "shown");
                f.support.transition && this.$element[e](this.$element[0][g])
            }
        },
        hide: function() {
            var e;
            this.transitioning || (e = this.dimension(), this.reset(this.$element[e]()), this.transition("removeClass", f.Event("hide"), "hidden"), this.$element[e](0))
        },
        reset: function(e) {
            var f = this.dimension();
            return this.$element.removeClass("collapse")[f](e || "auto")[0].offsetWidth,
            this.$element[null !== e ? "addClass": "removeClass"]("collapse"),
            this
        },
        transition: function(e, g, h) {
            var p = this,
            r = function() {
                "show" == g.type && p.reset();
                p.transitioning = 0;
                p.$element.trigger(h)
            };
            this.$element.trigger(g);
            g.isDefaultPrevented() || (this.transitioning = 1, this.$element[e]("in"), f.support.transition && this.$element.hasClass("collapse") ? this.$element.one(f.support.transition.end, r) : r())
        },
        toggle: function() {
            this[this.$element.hasClass("in") ? "hide": "show"]()
        }
    };
    f.fn.collapse = function(e) {
        return this.each(function() {
            var g = f(this),
            h = g.data("collapse"),
            p = "object" == typeof e && e;
            h || g.data("collapse", h = new n(this, p));
            "string" == typeof e && h[e]()
        })
    };
    f.fn.collapse.defaults = {
        toggle: !0
    };
    f.fn.collapse.Constructor = n;
    f(function() {
        f("body").on("click.collapse.data-api", "[data-toggle=collapse]",
        function(e) {
            var g = f(this),
            h;
            e = g.attr("data-target") || e.preventDefault() || (h = g.attr("href")) && h.replace(/.*(?=#[^\s]+$)/, "");
            h = f(e).data("collapse") ? "toggle": g.data();
            g[f(e).hasClass("in") ? "addClass": "removeClass"]("collapsed");
            f(e).collapse(h)
        })
    })
} (window.jQuery); !
function(f) {
    function n() {
        e(f(g)).removeClass("open")
    }
    function e(e) {
        var g = e.attr("data-target"),
        h;
        return g || (g = e.attr("href"), g = g && g.replace(/.*(?=#[^\s]*$)/, "")),
        h = f(g),
        h.length || (h = e.parent()),
        h
    }
    var g = "[data-toggle=dropdown]",
    h = function(e) {
        var g = f(e).on("click.dropdown.data-api", this.toggle);
        f("html").on("click.dropdown.data-api",
        function() {
            g.parent().removeClass("open")
        })
    };
    h.prototype = {
        constructor: h,
        toggle: function(g) {
            g = f(this);
            var h, s;
            if (!g.is(".disabled, :disabled")) return h = e(g),
            s = h.hasClass("open"),
            n(),
            s || (h.toggleClass("open"), g.focus()),
            !1
        },
        keydown: function(g) {
            var h, n, v;
            if (/(38|40|27)/.test(g.keyCode) && (h = f(this), g.preventDefault(), g.stopPropagation(), !h.is(".disabled, :disabled"))) {
                n = e(h);
                v = n.hasClass("open");
                if (!v || v && 27 == g.keyCode) return h.click();
                h = f("[role=menu] li:not(.divider) a", n);
                h.length && (n = h.index(h.filter(":focus")), 38 == g.keyCode && 0 < n && n--, 40 == g.keyCode && n < h.length - 1 && n++, ~n || (n = 0), h.eq(n).focus())
            }
        }
    };
    f.fn.dropdown = function(e) {
        return this.each(function() {
            var g = f(this),
            n = g.data("dropdown");
            n || g.data("dropdown", n = new h(this));
            "string" == typeof e && n[e].call(g)
        })
    };
    f.fn.dropdown.Constructor = h;
    f(function() {
        f("html").on("click.dropdown.data-api touchstart.dropdown.data-api", n);
        f("body").on("click.dropdown touchstart.dropdown.data-api", ".dropdown",
        function(e) {
            e.stopPropagation()
        }).on("click.dropdown.data-api touchstart.dropdown.data-api", g, h.prototype.toggle).on("keydown.dropdown.data-api touchstart.dropdown.data-api", g + ", [role=menu]", h.prototype.keydown)
    })
} (window.jQuery); !
function(f) {
    var n = function(e, g) {
        this.options = g;
        this.$element = f(e).delegate('[data-dismiss="modal"]', "click.dismiss.modal", f.proxy(this.hide, this));
        this.options.remote && this.$element.find(".modal-body").load(this.options.remote)
    };
    n.prototype = {
        constructor: n,
        toggle: function() {
            return this[this.isShown ? "hide": "show"]()
        },
        show: function() {
            var e = this,
            g = f.Event("show");
            this.$element.trigger(g); ! this.isShown && !g.isDefaultPrevented() && (f("body").addClass("modal-open"), this.isShown = !0, this.escape(), this.backdrop(function() {
                var g = f.support.transition && e.$element.hasClass("fade");
                e.$element.parent().length || e.$element.appendTo(document.body);
                e.$element.show();
                g && e.$element[0].offsetWidth;
                e.$element.addClass("in").attr("aria-hidden", !1).focus();
                e.enforceFocus();
                g ? e.$element.one(f.support.transition.end,
                function() {
                    e.$element.trigger("shown")
                }) : e.$element.trigger("shown")
            }))
        },
        hide: function(e) {
            e && e.preventDefault();
            e = f.Event("hide");
            this.$element.trigger(e);
            this.isShown && !e.isDefaultPrevented() && (this.isShown = !1, f("body").removeClass("modal-open"), this.escape(), f(document).off("focusin.modal"), this.$element.removeClass("in").attr("aria-hidden", !0), f.support.transition && this.$element.hasClass("fade") ? this.hideWithTransition() : this.hideModal())
        },
        enforceFocus: function() {
            var e = this;
            f(document).on("focusin.modal",
            function(f) {
                e.$element[0] !== f.target && !e.$element.has(f.target).length && e.$element.focus()
            })
        },
        escape: function() {
            var e = this;
            this.isShown && this.options.keyboard ? this.$element.on("keyup.dismiss.modal",
            function(f) {
                27 == f.which && e.hide()
            }) : this.isShown || this.$element.off("keyup.dismiss.modal")
        },
        hideWithTransition: function() {
            var e = this,
            g = setTimeout(function() {
                e.$element.off(f.support.transition.end);
                e.hideModal()
            },
            500);
            this.$element.one(f.support.transition.end,
            function() {
                clearTimeout(g);
                e.hideModal()
            })
        },
        hideModal: function(e) {
            this.$element.hide().trigger("hidden");
            this.backdrop()
        },
        removeBackdrop: function() {
            this.$backdrop.remove();
            this.$backdrop = null
        },
        backdrop: function(e) {
            var g = this.$element.hasClass("fade") ? "fade": "";
            if (this.isShown && this.options.backdrop) {
                var h = f.support.transition && g;
                this.$backdrop = f('<div class="modal-backdrop ' + g + '" />').appendTo(document.body);
                "static" != this.options.backdrop && this.$backdrop.click(f.proxy(this.hide, this));
                h && this.$backdrop[0].offsetWidth;
                this.$backdrop.addClass("in");
                h ? this.$backdrop.one(f.support.transition.end, e) : e()
            } else ! this.isShown && this.$backdrop ? (this.$backdrop.removeClass("in"), f.support.transition && this.$element.hasClass("fade") ? this.$backdrop.one(f.support.transition.end, f.proxy(this.removeBackdrop, this)) : this.removeBackdrop()) : e && e()
        }
    };
    f.fn.modal = function(e) {
        return this.each(function() {
            var g = f(this),
            h = g.data("modal"),
            p = f.extend({},
            f.fn.modal.defaults, g.data(), "object" == typeof e && e);
            h || g.data("modal", h = new n(this, p));
            "string" == typeof e ? h[e]() : p.show && h.show()
        })
    };
    f.fn.modal.defaults = {
        backdrop: !0,
        keyboard: !0,
        show: !0
    };
    f.fn.modal.Constructor = n;
    f(function() {
        f("body").on("click.modal.data-api", '[data-toggle="modal"]',
        function(e) {
            var g = f(this),
            h = g.attr("href"),
            p = f(g.attr("data-target") || h && h.replace(/.*(?=#[^\s]+$)/, "")),
            h = p.data("modal") ? "toggle": f.extend({
                remote: !/#/.test(h) && h
            },
            p.data(), g.data());
            e.preventDefault();
            p.modal(h).one("hide",
            function() {
                g.focus()
            })
        })
    })
} (window.jQuery); !
function(f) {
    var n = function(e, f) {
        this.init("tooltip", e, f)
    };
    n.prototype = {
        constructor: n,
        init: function(e, g, h) {
            var p, n;
            this.type = e;
            this.$element = f(g);
            this.options = this.getOptions(h);
            this.enabled = !0;
            "click" == this.options.trigger ? this.$element.on("click." + this.type, this.options.selector, f.proxy(this.toggle, this)) : "manual" != this.options.trigger && (p = "hover" == this.options.trigger ? "mouseenter": "focus", n = "hover" == this.options.trigger ? "mouseleave": "blur", this.$element.on(p + "." + this.type, this.options.selector, f.proxy(this.enter, this)), this.$element.on(n + "." + this.type, this.options.selector, f.proxy(this.leave, this)));
            this.options.selector ? this._options = f.extend({},
            this.options, {
                trigger: "manual",
                selector: ""
            }) : this.fixTitle()
        },
        getOptions: function(e) {
            return e = f.extend({},
            f.fn[this.type].defaults, e, this.$element.data()),
            e.delay && "number" == typeof e.delay && (e.delay = {
                show: e.delay,
                hide: e.delay
            }),
            e
        },
        enter: function(e) {
            var g = f(e.currentTarget)[this.type](this._options).data(this.type);
            if (!g.options.delay || !g.options.delay.show) return g.show();
            clearTimeout(this.timeout);
            g.hoverState = "in";
            this.timeout = setTimeout(function() {
                "in" == g.hoverState && g.show()
            },
            g.options.delay.show)
        },
        leave: function(e) {
            var g = f(e.currentTarget)[this.type](this._options).data(this.type);
            this.timeout && clearTimeout(this.timeout);
            if (!g.options.delay || !g.options.delay.hide) return g.hide();
            g.hoverState = "out";
            this.timeout = setTimeout(function() {
                "out" == g.hoverState && g.hide()
            },
            g.options.delay.hide)
        },
        show: function() {
            var e, f, h, p, n, s, v;
            if (this.hasContent() && this.enabled) {
                e = this.tip();
                this.setContent();
                this.options.animation && e.addClass("fade");
                s = "function" == typeof this.options.placement ? this.options.placement.call(this, e[0], this.$element[0]) : this.options.placement;
                f = /in/.test(s);
                e.remove().css({
                    top: 0,
                    left: 0,
                    display: "block"
                }).appendTo(f ? this.$element: document.body);
                h = this.getPosition(f);
                p = e[0].offsetWidth;
                n = e[0].offsetHeight;
                switch (f ? s.split(" ")[1] : s) {
                case "bottom":
                    v = {
                        top: h.top + h.height,
                        left: h.left + h.width / 2 - p / 2
                    };
                    break;
                case "top":
                    v = {
                        top: h.top - n,
                        left: h.left + h.width / 2 - p / 2
                    };
                    break;
                case "left":
                    v = {
                        top: h.top + h.height / 2 - n / 2,
                        left: h.left - p
                    };
                    break;
                case "right":
                    v = {
                        top: h.top + h.height / 2 - n / 2,
                        left: h.left + h.width
                    }
                }
                e.css(v).addClass(s).addClass("in")
            }
        },
        setContent: function() {
            var e = this.tip(),
            f = this.getTitle();
            e.find(".tooltip-inner")[this.options.html ? "html": "text"](f);
            e.removeClass("fade in top bottom left right")
        },
        hide: function() {
            function e() {
                var e = setTimeout(function() {
                    g.off(f.support.transition.end).remove()
                },
                500);
                g.one(f.support.transition.end,
                function() {
                    clearTimeout(e);
                    g.remove()
                })
            }
            var g = this.tip();
            return g.removeClass("in"),
            f.support.transition && this.$tip.hasClass("fade") ? e() : g.remove(),
            this
        },
        fixTitle: function() {
            var e = this.$element; (e.attr("title") || "string" != typeof e.attr("data-original-title")) && e.attr("data-original-title", e.attr("title") || "").removeAttr("title")
        },
        hasContent: function() {
            return this.getTitle()
        },
        getPosition: function(e) {
            return f.extend({},
            e ? {
                top: 0,
                left: 0
            }: this.$element.offset(), {
                width: this.$element[0].offsetWidth,
                height: this.$element[0].offsetHeight
            })
        },
        getTitle: function() {
            var e, f = this.$element,
            h = this.options;
            return e = f.attr("data-original-title") || ("function" == typeof h.title ? h.title.call(f[0]) : h.title),
            e
        },
        tip: function() {
            return this.$tip = this.$tip || f(this.options.template)
        },
        validate: function() {
            this.$element[0].parentNode || (this.hide(), this.$element = null, this.options = null)
        },
        enable: function() {
            this.enabled = !0
        },
        disable: function() {
            this.enabled = !1
        },
        toggleEnabled: function() {
            this.enabled = !this.enabled
        },
        toggle: function() {
            this[this.tip().hasClass("in") ? "hide": "show"]()
        },
        destroy: function() {
            this.hide().$element.off("." + this.type).removeData(this.type)
        }
    };
    f.fn.tooltip = function(e) {
        return this.each(function() {
            var g = f(this),
            h = g.data("tooltip"),
            p = "object" == typeof e && e;
            h || g.data("tooltip", h = new n(this, p));
            "string" == typeof e && h[e]()
        })
    };
    f.fn.tooltip.Constructor = n;
    f.fn.tooltip.defaults = {
        animation: !0,
        placement: "top",
        selector: !1,
        template: '<div class="tooltip"><div class="tooltip-arrow"></div><div class="tooltip-inner"></div></div>',
        trigger: "hover",
        title: "",
        delay: 0,
        html: !0
    }
} (window.jQuery); !
function(f) {
    var n = function(e, f) {
        this.init("popover", e, f)
    };
    n.prototype = f.extend({},
    f.fn.tooltip.Constructor.prototype, {
        constructor: n,
        setContent: function() {
            var e = this.tip(),
            f = this.getTitle(),
            h = this.getContent();
            e.find(".popover-title")[this.options.html ? "html": "text"](f);
            e.find(".popover-content > *")[this.options.html ? "html": "text"](h);
            e.removeClass("fade top bottom left right in")
        },
        hasContent: function() {
            return this.getTitle() || this.getContent()
        },
        getContent: function() {
            var e, f = this.$element,
            h = this.options;
            return e = f.attr("data-content") || ("function" == typeof h.content ? h.content.call(f[0]) : h.content),
            e
        },
        tip: function() {
            return this.$tip || (this.$tip = f(this.options.template)),
            this.$tip
        },
        destroy: function() {
            this.hide().$element.off("." + this.type).removeData(this.type)
        }
    });
    f.fn.popover = function(e) {
        return this.each(function() {
            var g = f(this),
            h = g.data("popover"),
            p = "object" == typeof e && e;
            h || g.data("popover", h = new n(this, p));
            "string" == typeof e && h[e]()
        })
    };
    f.fn.popover.Constructor = n;
    f.fn.popover.defaults = f.extend({},
    f.fn.tooltip.defaults, {
        placement: "right",
        trigger: "click",
        content: "",
        template: '<div class="popover"><div class="arrow"></div><div class="popover-inner"><h3 class="popover-title"></h3><div class="popover-content"><p></p></div></div></div>'
    })
} (window.jQuery); !
function(f) {
    function n(e, g) {
        var h = f.proxy(this.process, this),
        p = f(e).is("body") ? f(window) : f(e),
        n;
        this.options = f.extend({},
        f.fn.scrollspy.defaults, g);
        this.$scrollElement = p.on("scroll.scroll-spy.data-api", h);
        this.selector = (this.options.target || (n = f(e).attr("href")) && n.replace(/.*(?=#[^\s]+$)/, "") || "") + " .nav li > a";
        this.$body = f("body");
        this.refresh();
        this.process()
    }
    n.prototype = {
        constructor: n,
        refresh: function() {
            var e = this;
            this.offsets = f([]);
            this.targets = f([]);
            this.$body.find(this.selector).map(function() {
                var e = f(this),
                e = e.data("target") || e.attr("href"),
                h = /^#\w/.test(e) && f(e);
                return h && h.length && [[h.position().top, e]] || null
            }).sort(function(e, f) {
                return e[0] - f[0]
            }).each(function() {
                e.offsets.push(this[0]);
                e.targets.push(this[1])
            })
        },
        process: function() {
            var e = this.$scrollElement.scrollTop() + this.options.offset,
            f = (this.$scrollElement[0].scrollHeight || this.$body[0].scrollHeight) - this.$scrollElement.height(),
            h = this.offsets,
            p = this.targets,
            n = this.activeTarget,
            s;
            if (e >= f) return n != (s = p.last()[0]) && this.activate(s);
            for (s = h.length; s--;) n != p[s] && e >= h[s] && (!h[s + 1] || e <= h[s + 1]) && this.activate(p[s])
        },
        activate: function(e) {
            this.activeTarget = e;
            f(this.selector).parent(".active").removeClass("active");
            e = f(this.selector + '[data-target="' + e + '"],' + this.selector + '[href="' + e + '"]').parent("li").addClass("active");
            e.parent(".dropdown-menu").length && (e = e.closest("li.dropdown").addClass("active"));
            e.trigger("activate")
        }
    };
    f.fn.scrollspy = function(e) {
        return this.each(function() {
            var g = f(this),
            h = g.data("scrollspy"),
            p = "object" == typeof e && e;
            h || g.data("scrollspy", h = new n(this, p));
            "string" == typeof e && h[e]()
        })
    };
    f.fn.scrollspy.Constructor = n;
    f.fn.scrollspy.defaults = {
        offset: 10
    };
    f(window).on("load",
    function() {
        f('[data-spy="scroll"]').each(function() {
            var e = f(this);
            e.scrollspy(e.data())
        })
    })
} (window.jQuery); !
function(f) {
    var n = function(e) {
        this.element = f(e)
    };
    n.prototype = {
        constructor: n,
        show: function() {
            var e = this.element,
            g = e.closest("ul:not(.dropdown-menu)"),
            h = e.attr("data-target"),
            p,
            n;
            h || (h = e.attr("href"), h = h && h.replace(/.*(?=#[^\s]*$)/, ""));
            e.parent("li").hasClass("active") || (p = g.find(".active a").last()[0], n = f.Event("show", {
                relatedTarget: p
            }), e.trigger(n), n.isDefaultPrevented() || (h = f(h), this.activate(e.parent("li"), g), this.activate(h, h.parent(),
            function() {
                e.trigger({
                    type: "shown",
                    relatedTarget: p
                })
            })))
        },
        activate: function(e, g, h) {
            function p() {
                n.removeClass("active").find("> .dropdown-menu > .active").removeClass("active");
                e.addClass("active");
                s ? (e[0].offsetWidth, e.addClass("in")) : e.removeClass("fade");
                e.parent(".dropdown-menu") && e.closest("li.dropdown").addClass("active");
                h && h()
            }
            var n = g.find("> .active"),
            s = h && f.support.transition && n.hasClass("fade");
            s ? n.one(f.support.transition.end, p) : p();
            n.removeClass("in")
        }
    };
    f.fn.tab = function(e) {
        return this.each(function() {
            var g = f(this),
            h = g.data("tab");
            h || g.data("tab", h = new n(this));
            "string" == typeof e && h[e]()
        })
    };
    f.fn.tab.Constructor = n;
    f(function() {
        f("body").on("click.tab.data-api", '[data-toggle="tab"], [data-toggle="pill"]',
        function(e) {
            e.preventDefault();
            f(this).tab("show")
        })
    })
} (window.jQuery); !
function(f) {
    var n = function(e, g) {
        this.$element = f(e);
        this.options = f.extend({},
        f.fn.typeahead.defaults, g);
        this.matcher = this.options.matcher || this.matcher;
        this.sorter = this.options.sorter || this.sorter;
        this.highlighter = this.options.highlighter || this.highlighter;
        this.updater = this.options.updater || this.updater;
        this.$menu = f(this.options.menu).appendTo("body");
        this.source = this.options.source;
        this.shown = !1;
        this.listen()
    };
    n.prototype = {
        constructor: n,
        select: function() {
            var e = this.$menu.find(".active").attr("data-value");
            return this.$element.val(this.updater(e)).change(),
            this.hide()
        },
        updater: function(e) {
            return e
        },
        show: function() {
            var e = f.extend({},
            this.$element.offset(), {
                height: this.$element[0].offsetHeight
            });
            return this.$menu.css({
                top: e.top + e.height,
                left: e.left
            }),
            this.$menu.show(),
            this.shown = !0,
            this
        },
        hide: function() {
            return this.$menu.hide(),
            this.shown = !1,
            this
        },
        lookup: function(e) {
            var g;
            return this.query = this.$element.val(),
            !this.query || this.query.length < this.options.minLength ? this.shown ? this.hide() : this: (g = f.isFunction(this.source) ? this.source(this.query, f.proxy(this.process, this)) : this.source, g ? this.process(g) : this)
        },
        process: function(e) {
            var g = this;
            return e = f.grep(e,
            function(e) {
                return g.matcher(e)
            }),
            e = this.sorter(e),
            e.length ? this.render(e.slice(0, this.options.items)).show() : this.shown ? this.hide() : this
        },
        matcher: function(e) {
            return~e.toLowerCase().indexOf(this.query.toLowerCase())
        },
        sorter: function(e) {
            for (var f = [], h = [], p = [], n; n = e.shift();) n.toLowerCase().indexOf(this.query.toLowerCase()) ? ~n.indexOf(this.query) ? h.push(n) : p.push(n) : f.push(n);
            return f.concat(h, p)
        },
        highlighter: function(e) {
            var f = this.query.replace(/[\-\[\]{}()*+?.,\\\^$|#\s]/g, "\\$&");
            return e.replace(RegExp("(" + f + ")", "ig"),
            function(e, f) {
                return "<strong>" + f + "</strong>"
            })
        },
        render: function(e) {
            var g = this;
            return e = f(e).map(function(e, p) {
                return e = f(g.options.item).attr("data-value", p),
                e.find("a").html(g.highlighter(p)),
                e[0]
            }),
            e.first().addClass("active"),
            this.$menu.html(e),
            this
        },
        next: function(e) {
            e = this.$menu.find(".active").removeClass("active").next();
            e.length || (e = f(this.$menu.find("li")[0]));
            e.addClass("active")
        },
        prev: function(e) {
            e = this.$menu.find(".active").removeClass("active").prev();
            e.length || (e = this.$menu.find("li").last());
            e.addClass("active")
        },
        listen: function() {
            this.$element.on("blur", f.proxy(this.blur, this)).on("keypress", f.proxy(this.keypress, this)).on("keyup", f.proxy(this.keyup, this)); (f.browser.webkit || f.browser.msie) && this.$element.on("keydown", f.proxy(this.keydown, this));
            this.$menu.on("click", f.proxy(this.click, this)).on("mouseenter", "li", f.proxy(this.mouseenter, this))
        },
        move: function(e) {
            if (this.shown) {
                switch (e.keyCode) {
                case 9:
                case 13:
                case 27:
                    e.preventDefault();
                    break;
                case 38:
                    e.preventDefault();
                    this.prev();
                    break;
                case 40:
                    e.preventDefault(),
                    this.next()
                }
                e.stopPropagation()
            }
        },
        keydown: function(e) {
            this.suppressKeyPressRepeat = !~f.inArray(e.keyCode, [40, 38, 9, 13, 27]);
            this.move(e)
        },
        keypress: function(e) {
            this.suppressKeyPressRepeat || this.move(e)
        },
        keyup: function(e) {
            switch (e.keyCode) {
            case 40:
            case 38:
                break;
            case 9:
            case 13:
                if (!this.shown) return;
                this.select();
                break;
            case 27:
                if (!this.shown) return;
                this.hide();
                break;
            default:
                this.lookup()
            }
            e.stopPropagation();
            e.preventDefault()
        },
        blur: function(e) {
            var f = this;
            setTimeout(function() {
                f.hide()
            },
            150)
        },
        click: function(e) {
            e.stopPropagation();
            e.preventDefault();
            this.select()
        },
        mouseenter: function(e) {
            this.$menu.find(".active").removeClass("active");
            f(e.currentTarget).addClass("active")
        }
    };
    f.fn.typeahead = function(e) {
        return this.each(function() {
            var g = f(this),
            h = g.data("typeahead"),
            p = "object" == typeof e && e;
            h || g.data("typeahead", h = new n(this, p));
            "string" == typeof e && h[e]()
        })
    };
    f.fn.typeahead.defaults = {
        source: [],
        items: 8,
        menu: '<ul class="typeahead dropdown-menu"></ul>',
        item: '<li><a href="#"></a></li>',
        minLength: 1
    };
    f.fn.typeahead.Constructor = n;
    f(function() {
        f("body").on("focus.typeahead.data-api", '[data-provide="typeahead"]',
        function(e) {
            var g = f(this);
            g.data("typeahead") || (e.preventDefault(), g.typeahead(g.data()))
        })
    })
} (window.jQuery); !
function(f) {
    var n = function(e, g) {
        this.options = f.extend({},
        f.fn.affix.defaults, g);
        this.$window = f(window).on("scroll.affix.data-api", f.proxy(this.checkPosition, this));
        this.$element = f(e);
        this.checkPosition()
    };
    n.prototype.checkPosition = function() {
        if (this.$element.is(":visible")) {
            var e = f(document).height(),
            g = this.$window.scrollTop(),
            h = this.$element.offset(),
            p = this.options.offset,
            n = p.bottom,
            s = p.top;
            "object" != typeof p && (n = s = p);
            "function" == typeof s && (s = p.top());
            "function" == typeof n && (n = p.bottom());
            e = null != this.unpin && g + this.unpin <= h.top ? !1 : null != n && h.top + this.$element.height() >= e - n ? "bottom": null != s && g <= s ? "top": !1;
            this.affixed !== e && (this.affixed = e, this.unpin = "bottom" == e ? h.top - g: null, this.$element.removeClass("affix affix-top affix-bottom").addClass("affix" + (e ? "-" + e: "")))
        }
    };
    f.fn.affix = function(e) {
        return this.each(function() {
            var g = f(this),
            h = g.data("affix"),
            p = "object" == typeof e && e;
            h || g.data("affix", h = new n(this, p));
            "string" == typeof e && h[e]()
        })
    };
    f.fn.affix.Constructor = n;
    f.fn.affix.defaults = {
        offset: 0
    };
    f(window).on("load",
    function() {
        f('[data-spy="affix"]').each(function() {
            var e = f(this),
            g = e.data();
            g.offset = g.offset || {};
            g.offsetBottom && (g.offset.bottom = g.offsetBottom);
            g.offsetTop && (g.offset.top = g.offsetTop);
            e.affix(g)
        })
    })
} (window.jQuery); (function() {
    function f(e) {
        var f = {};
        $(":input", e).each(function(e, n) {
            var r = $(n),
            s = $.trim($(n).attr("name"));
            s && (f[s] = $.trim(r.val()))
        });
        return f
    }
    $("input, textarea").placeholder();
    $("a.forgot").click(function() {
        $("#login-modal").modal("hide");
        $("#forgetform").modal({
            show: !0
        })
    });
    signup_error && $("#signup-modal").modal({
        show: !0
    });
    send_error && $("#forgetform").modal({
        show: !0
    });
    login_error && $("#login-modal").modal({
        show: !0
    });
    show_activation && $("#activation-modal").modal({
        show: !0
    });
    reset_error && $("#setpassword-modal").modal({
        show: !0
    });
    $("form").submit(function(e) {
        var f = !1;
        $(this).find(":input").each(function(e, n) {
            var r = $(n);
            "email" == r.attr("name") && !$.trim(r.val()) && (r.addClass("empty-error"), r.val("\u8bf7\u8f93\u5165\u90ae\u7bb1"), f = !0)
        });
        return ! f
    });
    var n = $(".password-form");
    n.submit(function() {
        var e = f(n);
        if (e.password && e.password === e.password2) return ! 0;
        $(n.find(":password")).addClass("empty-error");
        return ! 1
    });
    $("input").keyup(function(e) {
        e = $(this);
        $.trim(e.val()) && e.removeClass("empty-error")
    });
    window.console && window.console.log && console.log("\u5bf9\u8fd9\u4e2a\u7f51\u7ad9\u611f\u5174\u8da3\uff1f\u8003\u8651\u52a0\u5165\u6211\u4eec\u56e2\u961f\uff1f\u90ae\u4ef6\u4f60\u7684github/delicious/meiweisq id \u7ed9\u6211\u4eec\uff1acn-jobs@avos.com");
    $(".signup-form .reg").click(function() {
        $form = $(".signup-form");
        if ($form.find("[name='password']").val() != $form.find("[name='password1']").val()) return $form.find(".error").text("\u4e24\u6b21\u8f93\u5165\u5bc6\u7801\u4e0d\u4e00\u81f4"),
        !1;
        $.post("/fast-signup", {
            email: $form.find("[name='email']").val(),
            password: $form.find("[name='password']").val(),
            username: $form.find("[name='username']").val()
        },
        function(e) {
            e.error ? $form.find(".error").text(e.error) : window.location.href = "/?step=1&new=1"
        })
    });
    $(".login-bind-tp a").click(function(e) {
        e.preventDefault();
        e = $(this).attr("href");
        window.open(e, "_blank", "width=650, height=500,left=" + (screen.width / 2 - 325) + ",top=" + (screen.height / 2 - 250))
    });
    $(".third-parties p").click(function() {
        var e = $(this).attr("data-url");
        window.open(e, "_blank", "width=650, height=500,left=" + (screen.width / 2 - 325) + ",top=" + (screen.height / 2 - 250))
    })
})();