!
function () {
    var globalIndex = 0;
    var globalImageTimeout;
    var globalAnomation;
    var a = {
        HEIGHT: $(window).height(),
        partEl: $(".part"),
        changing: !1,
        currentIndex: 0,
        currentEl: null,
        nextEl: null,
        nextIndex: 0,
        scrollEl: null,
        currentZIndex: 1e3,
        direction: "",
        animating: !1,
        stopScroll: !1,
        scrollTime: 0,
        init: function () {
            a.judgeBrowser() ? (a.currentEl = $(a.partEl[a.currentIndex]), a.view.initDOM(), a.attachEvent.initEvent(), a.activity.start()) : ($(".browser-true-body").hide(), $(".browser-error").show())
        },
        judgeBrowser: function () {
            return $.browser.msie && parseInt($.browser.version) <= 5 ? !1 : !0//判断浏览器是否为ie且版本是否小于5
        },
        activity: {
            start: function () {
                var a = (new Date).getTime(),
                    b = new Date(2015, 1, 26).getTime();
                b > a && this.init()
            }
        },
        view: {
            initDOM: function () {
                for (var b = 0; b < a.partEl.length; b++) $(a.partEl[b]).css({
                    height: a.HEIGHT
                });
                var c = a.Tool.getUrlParam("gotoDownload");
                c && (a.currentIndex = 4),
                $(a.partEl[a.currentIndex]).css({
                    "z-index": a.currentZIndex+1
                }),
                $(".register-content").css({
                    top: a.HEIGHT
                }),
                $(".first-content").css("height", a.HEIGHT ),
                a.view.drawNav(),
                a.view.lazyLoad(a.currentIndex),
                $(".iphone").css("height", a.HEIGHT)
            },
            drawNav: function () {
                $(".point-nav").find(".checked").removeClass("checked"),
                $(".point-nav").find("li").each(function (b) {
                    b == a.currentIndex && $(this).addClass("checked")
                })
            },
         
            lazyLoad: function (a) {
                var b = {
                    page_0: {
                        logo: "../resources/images/qj-white-logo.png",
                        text: "./images/title.png",
                        bg: "../resources/images/background_first.png",
                        arrow: "../resources/images/arrow.png"
                    },
                    
                    page_1: {
                        ps_1:  "./images/man_first.png",
                        ps_2:  "./images/page_cha1.png",
                        ps_3:  "./images/page_cha2.png",
                        ps_4:  "./images/page_cha3.png",
                        ps_5:  "./images/page_cha4.png",
                        ps_6:  "./images/man_second.png",
                        ps_7:  "./images/page_cha5.png",
                        ps_8:  "./images/page_cha6.png",
                        ps_9:  "./images/page_cha7.png"
                    },

                     page_2: {
                        phone_content_1: "./images/bd-one.png",
                        phone_content_2: "./images/bd-two.png",
                        phone_content_3: "./images/bd-three.png",
                        phone: "./images/page2.png"
                    },
                    page_3: {
                        ps_1: "./images/41.png",
                        ps_2: "./images/42.png",
                        ps_3: "./images/43.png",
                        ps_4: "./images/44.png",
                        ps_5: "./images/45.png"
                    }, 
                   page_4: {
                        ps_1: "./images/51.png",
                        ps_2: "./images/52.png",
                        ps_3: "./images/53.png",
                        ps_4: "./images/54.png",
                        ps_5: "./images/56.png",
                        ps_6: "./images/57.png",
                        ps_7: "./images/55.png",
                        ps_8: "./images/58.png"
                    }                                   
                    			
                };
                switch (a) {
                case 0:
                    $(".header").find(".logo").attr("src", b.page_0.logo),
                    $("#first").find(".first-content").css({
                        background: "url(" + b.page_0.bg + ") no-repeat center center",
                        "background-size": "cover"
                    }).end().find(".arrow").css({
                        background: "url(" + b.page_0.arrow + ") no-repeat center bottom"
                    }).end().find(".first-text").find("img").attr("src", b.page_0.text);
                    break;
                case 1:
                    $("#qijiaMessage").find(".message-left-owner").eq(0).attr("src", b.page_1.ps_1).end().end().find(".message-left-message").eq(0).attr("src", b.page_1.ps_2).end().end().find(".message-left-message").eq(1).attr("src", b.page_1.ps_3).end().end().find(".message-left-message").eq(2).attr("src", b.page_1.ps_4).end().end().find(".message-left-message").eq(3).attr("src", b.page_1.ps_5).end().end().find(".message-left-owner").eq(1).attr("src", b.page_1.ps_6).end().end().find(".message-left-message").eq(4).attr("src", b.page_1.ps_7).end().end().find(".message-left-owner").eq(2).attr("src", b.page_1.ps_8).end().end().find(".message-left-message").eq(5).attr("src", b.page_1.ps_9);
                    break;
                case 2:
                    $("#peopleMessage").find(".iphone").css({
                       /* background: "url(" + b.page_2.phone + ") no-repeat center center",*/
                       /* "background-size": "100%",*/
                        /*"background-position": "0 -80px"*/
                    }).end().find(".person-message-list").each(function (a) {
                        $(this).attr("src", b.page_2["phone_content_" + (a + 1)])
                    });
                    break;  
                case 3:
                    $("#qijiaMessageScan").find(".message-left-owner").attr("src", b.page_3.ps_1).end().end().find(".message-left-message").eq(0).attr("src", b.page_3.ps_2).end().end().find(".message-left-message").eq(1).attr("src", b.page_3.ps_3).end().end().find(".message-left-message").eq(2).attr("src", b.page_4.ps_3).end().end().find(".message-left-message").eq(3).attr("src", b.page_3.ps_5);
                    break; 
                case 4:
                    $("#messageType").find(".message-left-owner").attr("src", b.page_4.ps_1).end().end().find(".message-left-message").eq(0).attr("src", b.page_4.ps_2).end().end().find(".message-left-message").eq(1).attr("src", b.page_4.ps_3).end().end().find(".message-left-message").eq(2).attr("src", b.page_4.ps_4).end().end().find(".message-left-message").eq(3).attr("src", b.page_4.ps_5).end().end().find(".message-left-message").eq(4).attr("src", b.page_4.ps_6).end().end().find(".message-left-message").eq(5).attr("src", b.page_4.ps_7).end().end().find(".message-left-message").eq(6).attr("src", b.page_4.ps_8);
                    break;
                
                default:
                    return
                }
            },

            popUpIn: function () {
                return a.changing || a.animating ? !1 : (a.animating = !0, void a.OutAnimate[0](function () {
                    a.nextIndex = 0,
                    a.currentIndex = 4,
                    a.nextEl = $(a.partEl[a.nextIndex]),
                    a.currentEl = $(a.partEl[a.currentIndex]),
                    a.direction = "bottom",
                    a.animating = !1,
                    a.attachEvent.changePage()
                }))
            },
            setZIndex: function () {
                a.currentEl.css("zIndex", a.currentZIndex + 1),
                a.nextEl.css("zIndex", a.currentZIndex - 1)
            },
        },
        attachEvent: {
            initEvent: function () {
                $.browser.chrome || $.browser.safari ? (a.showAnimateName = Math.random() >= .5 ? "rotate" : "scale", a.showAnimateName = "scroll") : a.showAnimateName = "scroll",
                a.attachEvent.scroll(),
                a.attachEvent.clickNav(),
                a.attachEvent.clickHeadNav(),
                a.attachEvent.resize(),
                a.attachEvent.clickKey()
            },
            scroll: function () {
                $("html").bind("mousewheel DOMMouseScroll", function (b) {
                    a.Tool.stopDefault(b);
                    var c = (new Date).getTime();
                    if (c - a.scrollTime < 1400) return !1;
                    if (a.scrollTime = c, !a.stopScroll && !a.changing && !a.animating) {
                        a.animating = !0;
                        var d = parseInt(b.originalEvent.wheelDelta || -b.originalEvent.detail);
                        if (d > 0 && 0 == a.currentIndex) return void(a.animating = !1);
                        if (0 > d && 4 == a.currentIndex) return void(a.animating = !1);
                        a.nextIndex = a.currentIndex,
                        0 > d ? (a.currentIndex = a.currentIndex + 1 > 4 ? 4 : a.currentIndex + 1, a.direction = "bottom") : (a.currentIndex = a.currentIndex - 1 < 0 ? 0 : a.currentIndex - 1, a.direction = "top"),
                        a.nextEl = $(a.partEl[a.nextIndex]),
                        a.currentEl = $(a.partEl[a.currentIndex]),
                        a.animating = !1,
                        a.attachEvent.changePage()
                    }
                    //handle index=0 issue
                    if (a.currentIndex == 0 || a.currentIndex == 1 || a.currentIndex == 2 || a.currentIndex == 3 || a.currentIndex == 4) {
                        globalIndex = 0;
                        $(".message-left-owner, .message-left-message").css("opacity", 0);
                        $(".message-left-owner, .message-left-message, .pml0, .pml1, .pml2").stop(true);
                        if (globalImageTimeout) {
                            clearTimeout(globalImageTimeout);
                        }
                    }
                })
            },
            clickNav: function () {
                $(".point-nav").on("click", "li", function () {
                    var b = $(this).index(),
                        c = a.currentIndex;
                    if (b != c) {
                            if (a.changing || a.animating) return !1;
                            a.animating = !0,
                            a.nextIndex = c,
                            a.currentIndex = b,
                            a.nextEl = $(a.partEl[a.nextIndex]),
                            a.currentEl = $(a.partEl[a.currentIndex]),
                            a.direction = b > c ? "bottom" : "top",
                            a.animating = !1,
                            a.changing = a.attachEvent.changePage()
                        }
                })
            },
            clickHeadNav: function () {
                $(".arrow").on("click", function () {
                    $(".point-nav").find("li").eq(1).trigger("click")
                }),
                $(".head-face").on("click", function (b) {
                    b.preventDefault(),
                    $(".nav").find(".checked").removeClass("checked"),
                    $(this).addClass("checked"),
                    a.HEIGHT <= 650 ? $(".register-content").animate({
                        top: a.HEIGHT
                    }, 400) : $(".register-content").animate({
                        top: a.HEIGHT
                    }, 400)
                })
            },
            clickKey: function () {
                $(document).keydown(function (b) {
                    40 == b.keyCode && 4 != a.currentIndex ? $(".point-nav").find("li").eq(a.currentIndex + 1).trigger("click") : 38 == b.keyCode && 0 != a.currentIndex && $(".point-nav").find("li").eq(a.currentIndex - 1).trigger("click")
                })
            },
            changePage: function () {
                return 1 != a.changing ? (a.view.lazyLoad(a.currentIndex), a.changing = !0, a.OutAnimate[a.currentIndex](), a.showAnimate[a.showAnimateName](function () {
                    a.animating = !0,
                    a.InAnimate[a.currentIndex]()
                }), a.view.drawNav(), a.changing) : void 0
            },
            resize: function () {
                $(window).on("resize", function () {
                    a.HEIGHT = $(window).height();
                    for (var b = 0; b < a.partEl.length; b++) $(a.partEl[b]).css({
                        height: a.HEIGHT
                    });
                    0 == a.currentIndex && ($(".register-content").css({
                        top: a.HEIGHT
                    }), $(".nav").find(".checked").removeClass("checked"), $(".nav").find(".head-face").addClass("checked"), $(".first-content").css("height", a.HEIGHT)),
                    $(".iphone").css("height", a.HEIGHT)
                })
            }
        },

        showAnimate: {
            scroll: function (b) {
                "bottom" == a.direction ? (a.currentEl.css({
                    top: a.HEIGHT,
                    "z-index": a.currentZIndex + 1
                }).animate({
                    top: 0
                }, 500, function () {
                    a.changing = !1,
                    a.nextEl.css({
                        "z-index": 0
                    }),
                    b && b()
                }), a.nextEl.animate({
                    top: -a.HEIGHT,
                    "z-index": a.currentZIndex
                }, 500)) : "top" == a.direction && (a.currentEl.css({
                    top: -a.HEIGHT,
                    "z-index": a.currentZIndex + 1
                }).animate({
                    top: 0
                }, 500, function () {
                    a.changing = !1,
                    a.nextEl.css({
                        "z-index": 0
                    }),
                    b && b()
                }), a.nextEl.animate({
                    top: a.HEIGHT,
                    "z-index": a.currentZIndex
                }, 500))
            },
            scale: function (b) {
                "bottom" == a.direction ? a.currentEl.css({
                    "-webkit-transform": "scale(1)",
                    top: a.HEIGHT,
                    "z-index": a.currentZIndex + 1
                }).animate({
                    top: 0
                }, 500, function () {
                    a.changing = !1,
                    a.nextEl.css({
                        "-webkit-transition": "",
                        "z-index": 0
                    }),
                    b && b()
                }) : "top" == a.direction && a.currentEl.css({
                    "-webkit-transform": "scale(1)",
                    top: -a.HEIGHT,
                    "z-index": a.currentZIndex + 1
                }).animate({
                    top: 0
                }, 500, function () {
                    a.changing = !1,
                    a.nextEl.css({
                        "-webkit-transition": "",
                        "z-index": 0
                    }),
                    b && b()
                }),
                a.nextEl.css({
                    "-webkit-transition": "transform 500ms ease-in",
                    "-webkit-transform": "scale(0.8)",
                    "z-index": a.currentZIndex
                })
            },
            rotate: function (b) {
                $(".parent-box").addClass("pt-perspective"),
                "bottom" == a.direction ? (a.nextEl.addClass("pt-page-flipOutBottom"), setTimeout(function () {
                    a.currentEl.css({
                        "z-index": a.currentZIndex + 1
                    }),
                    a.nextEl.css({
                        "z-index": 0
                    }),
                    a.nextEl.removeClass("pt-page-flipOutBottom"),
                    a.currentEl.addClass("pt-page-flipInTop"),
                    setTimeout(function () {
                        a.currentEl.removeClass("pt-page-flipInTop"),
                        $(".parent-box").removeClass("pt-perspective"),
                        a.changing = !1,
                        b && b()
                    }, 500)
                }, 500)) : "top" == a.direction && (a.nextEl.addClass("pt-page-flipOutTop"), setTimeout(function () {
                    a.currentEl.css({
                        "z-index": a.currentZIndex + 1
                    }),
                    a.nextEl.css({
                        "z-index": 0
                    }),
                    a.nextEl.removeClass("pt-page-flipOutTop"),
                    a.currentEl.addClass("pt-page-flipInBottom"),
                    setTimeout(function () {
                        a.currentEl.removeClass("pt-page-flipInBottom"),
                        $(".parent-box").removeClass("pt-perspective"),
                        a.changing = !1,
                        b && b()
                    }, 500)
                }, 500))
            },
            cover: function (b) {                 
                "bottom" == a.direction ? a.currentEl.css({
                    opacity: 1,
                    top: a.HEIGHT,
                    transform: "translateZ(0)",
                    "z-index": a.currentZIndex + 1
                }).animate({
                    top: 0
                }, 600, function () {
                    a.changing = !1,
                    a.nextEl.css({
                        opacity: .5,
                        "z-index": 0
                    }),
                    b && b()
                }) : "top" == a.direction && a.currentEl.css({
                    opacity: 1,
                    top: -a.HEIGHT,
                    "z-index": a.currentZIndex + 1
                }).animate({
                    top: 0
                }, 600, function () {
                    a.changing = !1,
                    a.nextEl.css({
                        opacity: .5,
                        "z-index": 0
                    }),
                    b && b()
                }),
                a.nextEl.css({
                    transform: "translateZ(0)",
                    "z-index": a.currentZIndex
                }).animate({
                    opacity: .5
                }, 600)
            }
        },
        Tool: {
            stopDefault: function (a) {
                return a && a.preventDefault ? a.preventDefault() : window.event.returnValue = !1,
                !1
            },
            getUrlParam: function (a) {
                var b = new RegExp("(^|&)" + a + "=([^&]*)(&|$)"),
                    c = window.location.search.substr(1).match(b);
                return null != c ? unescape(c[2]) : null
            },
            getQueryString: function (a, b) {
                if (-1 == b.indexOf("?") || -1 == b.indexOf(a + "=")) return "";
                for (var c, d, e, f = b.substring(b.indexOf("?") + 1), g = f.split("&"), h = 0; h < g.length; h++) if (c = g[h].indexOf("="), -1 != c && (d = g[h].substring(0, c), e = g[h].substring(c + 1), d == a)) return unescape(e.replace("+", " "));
                return ""
            },
            getQdValue: function () {
                var b = a.Tool.getQueryString("lwfrom", location.href),
                    c = document.referrer;
                return "" == b && c && (a.Tool.getQueryString("lwfrom", c) ? b = a.Tool.getQueryString("lwfrom", c) : new RegExp("baidu.com").test(c) ? b = "baidu" : new RegExp("google.com").test(c) ? b = "google" : new RegExp("google.cn").test(c) ? b = "google" : new RegExp("so.com").test(c) ? b = "360" : new RegExp("sogou.com").test(c) ? b = "sogou" : new RegExp("bing.com").test(c) ? b = "bing" : new RegExp("soso.com").test(c) && (b = "soso")),
                b
            }
        },
        InAnimate: [function () {
            a.animating = !1
        },
 

       function(){
         if (globalIndex != a.currentIndex) {
                $(".message-left-owner, .message-left-message").css("opacity", 0);
                 $(".message-left-owner, .message-left-message, .pml0, .pml1, .pml2").stop(true);                
               globalIndex = a.currentIndex;
                a.animating=!1;//判断是否是动画页
                if (globalAnomation) {
                    clearTimeout(globalAnomation);
                }
                if (globalImageTimeout) {
                    clearTimeout(globalImageTimeout);
                }
                globalAnomation = setTimeout(function() {
                  $("#qijiaMessage").find(".message-left-owner").eq(0).animate({
                        opacity:1                
                    },400,function(){
                        $("#qijiaMessage").find(".message-left-message").eq(0).animate({
                            opacity:1
                        },400,function(){
                            $("#qijiaMessage").find(".message-left-message").eq(1).animate({
                                opacity:1
                            },400,function(){
                                $("#qijiaMessage").find(".message-left-message").eq(2).animate({
                                    opacity:1
                                },400,function(){
                                    $("#qijiaMessage").find(".message-left-message").eq(3).animate({
                                        opacity:1
                                    },100,function(){
                                        $("#qijiaMessage").find(".message-left-owner").eq(0).animate({
                                            opacity:0
                                        },100,function(){
                                            $("#qijiaMessage").find(".message-left-message").eq(0).animate({
                                                opacity:0
                                            },100,function(){
                                                $("#qijiaMessage").find(".message-left-message").eq(1).animate({
                                                    opacity:0
                                                },100,function(){
                                                    $("#qijiaMessage").find(".message-left-message").eq(2).animate({
                                                        opacity:0
                                                    },100,function(){
                                                        $("#qijiaMessage").find(".message-left-message").eq(3).animate({
                                                            opacity:0
                                                        },800,function(){
                                                            $("#qijiaMessage").find(".message-left-owner").eq(1).animate({
                                                                opacity:1
                                                            },800,function(){
                                                                $("#qijiaMessage").find(".message-left-message").eq(4).animate({
                                                                    opacity:1
                                                                },300,function(){
                                                                    $("#qijiaMessage").find(".message-left-owner").eq(1).animate({
                                                                        opacity:0
                                                                    },200,function(){
                                                                        $("#qijiaMessage").find(".message-left-message").eq(4).animate({
                                                                            opacity:0
                                                                        },1200,function(){
                                                                            $("#qijiaMessage").find(".message-left-owner").eq(2).animate({
                                                                                opacity:1
                                                                            },1200,function(){
                                                                                $("#qijiaMessage").find(".message-left-message").eq(5).animate({
                                                                                    opacity:1
                                                                                })
                                                                            })
                                                                        })
                                                                    })
                                                                })
                                                            })
                                                        })
                                                    })
                                                })
                                            })
                                        })
                                    })
                                })
                            })
                        })
                    })
                }); 
            } 
        },

         function () {
         
        if(globalIndex != a.currentIndex){
                $(".message-left-owner, .message-left-message").css("opacity", 0);
                $(".message-left-owner, .message-left-message").stop(true);  
                $(".pointer").removeClass("pointer-animate");
                $(".pointer-bg").css("opacity", 0);
                $(".pml0,.pml1,.pml2").css("opacity",0);
                $(".pml0,.pml1,.pml2").stop(true, true);
                globalIndex = a.currentIndex;
                a.animating=!1;
        }
        if (globalIndex == a.currentIndex) {
                $(".phone-animate-stage,.person-message-list, .pml0, .pml1, .pml2").css("opacity",1);   
                $(".phone-animate-stage,.person-message-list, .pml0, .pml1, .pml2").animate({opacity:1}); 
                if (globalAnomation) {
                    clearTimeout(globalAnomation);
                }
                //再次加载图片时，清除上次显示的图片
                if (globalImageTimeout) {
                    clearTimeout(globalImageTimeout);
                }
                globalAnomation==  2 ==setTimeout(function () {
                    $(".pointer").addClass("pointer-animate");
                    $(".pointer-bg").css("opacity", 1);
                   //再次加载动画时，清除上一次动画
                           
                globalImageTimeout== setTimeout(function() {
                    $(".pointer").removeClass("pointer-animate"),
                    $(".pointer-bg").css("opacity", 0),
                    2 == a.currentIndex && ($(".pml0, .pml1, .pml2").css("opacity", 1),$(".pointer").addClass("pointer2"), $(".pointer-bg").addClass("pointer-bg2"), $(".pml0").animate({
                        left: 0,
                        transform: "translateZ(0)"
                    }, 1000), $(".pml1").animate({
                        left: 0,
                        transform: "translateZ(0)"
                    }, 1000, function () {

                            return 2 !=  a.currentIndex ? ( $(".pml0,.pml1,.pml2").stop(true, true),$(".pointer").removeClass("pointer2"), void $(".pointer-bg").removeClass("pointer-bg2")) : ($(".pointer").addClass("pointer-animate"), $(".pointer-bg").css("opacity", 1), void setTimeout(function () {
                                clearTimeout(globalAnomation);
                               $(".pml0, .pml1, .pml2").css("opacity", 1),
                                $(".pointer").removeClass("pointer-animate").removeClass("pointer2"),
                                $(".pointer-bg").css("opacity", 0).removeClass("pointer-bg2"),                             
                                $(".pml2").animate({
                                    left: 0,
                                    transform: "translateZ(0)"
                                }, 1000),
                                $(".pml2").animate({
                                    left: "100%",
                                    transform: "translateZ(0)"
                                }, 1000),
                                $(".pml1").animate({
                                    left: 0,
                                    transform: "translateZ(0)"
                                }, 1000),
                                $(".pml1").animate({
                                    left: 0,
                                    transform: "translateZ(0)"
                                }, 1000),
                                  $(".pml0").animate({
                                    left: 0,
                                    transform: "translateZ(0)"
                                }, 1000),

                                 $(".pml2").animate({
                                    left: "100%",
                                    transform: "translateZ(0)"
                                }, 1000),
                                 $(".pml1").animate({
                                    left: "100%",
                                    transform: "translateZ(0)"
                                }, 1000),
                               
                                  $(".pml0").animate({
                                    left: 0,
                                    transform: "translateZ(0)"
                            }, 1000)
                        }, 1000))
                    }))
                });
            }, 1000);      
            };
        },
          
        //last 2
       function(){
            if (globalIndex != a.currentIndex) {
                $(".message-left-owner, .message-left-message").css("opacity", 0);
                $(".message-left-owner, .message-left-message, .pml0, .pml1, .pml2").stop(true);
               globalIndex = a.currentIndex;
                a.animating=!1;
                if (globalAnomation) {
                    clearTimeout(globalAnomation);
                }
                if (globalImageTimeout) {
                            clearTimeout(globalImageTimeout);
                        }
                globalAnomation = setTimeout(function() {
                  $("#qijiaMessageScan").find(".message-left-owner").eq(0).animate({
                        opacity:1                
                    },400,function(){
                        $("#qijiaMessageScan").find(".message-left-message").eq(0).animate({
                            opacity:1
                        },400,function(){
                            $("#qijiaMessageScan").find(".message-left-message").eq(1).animate({
                                opacity:1
                            },400,function(){
                                $("#qijiaMessageScan").find(".message-left-message").eq(2).animate({
                                    opacity:1
                                },400,function(){
                                    $("#qijiaMessageScan").find(".message-left-message").eq(3).animate({
                                        opacity:1
                                    })
                                })
                            })
                        })
                    })
                }); 
            }
        },

     
        //最后一页
        function(){
           // alert(a.currentIndex)
            if (globalIndex != a.currentIndex) {
                $(".message-left-owner, .message-left-message").css("opacity", 0);
                $(".message-left-owner, .message-left-message, .pml0, .pml1, .pml2").stop(true);
               globalIndex = a.currentIndex;
                a.animating=!1;
                if (globalAnomation) {
                    clearTimeout(globalAnomation);
                }
                if (globalImageTimeout) {
                            clearTimeout(globalImageTimeout);
                        }
                globalAnomation = setTimeout(function() {
                  $("#messageType").find(".message-left-owner").eq(0).animate({
                        opacity:1
                    },400,function(){
                       $("#messageType").find(".message-left-message").eq(0).animate({
                            opacity:1
                       },400,function(){
                         $("#messageType").find(".message-left-message").eq(1).animate({
                            opacity:1
                         },400,function(){
                            $("#messageType").find(".message-left-message").eq(2).animate({
                                opacity:1
                            },400,function(){
                                $("#messageType").find(".message-left-message").eq(3).animate({
                                    opacity:1
                                },400,function(){
                                    $("#messageType").find(".message-left-message").eq(4).animate({
                                        opacity:1
                                    },400,function(){
                                        $("#messageType").find(".message-left-message").eq(5).animate({
                                            opacity:1
                                        },400,function(){
                                            $("#messageType").find(".message-left-message").eq(6).animate({
                                                opacity:1
                                            })
                                        })
                                    })
                                })
                            })
                        })
                     }) 
                })
                }); 
            } 
        },

        function () {
            a.animating = !1
        },


        function () {
            a.animating = !1
        }],
        OutAnimate: [function (a) {
            a && a()
        },

        function (a) {
            $(".pml0").css({
                left: "0"
            }),
            $(".pml1").css({
                left: "100%"
            }),
            $(".pml2").css({
                left: "100%"
            }),
            a && a()
        },

        function (a) {
            $("#qijiaMessage").find(".message-left-owner").eq(0).animate({
                opacity: 0
            }).end().find(".message-left-message").eq(0).animate({
                opacity: 0
            }).end().find(".message-left-message").eq(1).animate({
                opacity: 0
            }).end().find(".message-left-message").eq(2).animate({
                opacity: 0
            }).end().find(".message-left-message").eq(3).animate({
                opacity: 0
            }).end().find(".message-left-owner").eq(1).animate({
                opacity: 0
            }).end().find(".message-left-message").eq(4).animate({
                opacity: 0
            }).end().find(".message-left-owner").eq(2).animate({
                opacity: 0
            }).end().find(".message-left-message").eq(5).animate({
                opacity: 0
            }),
            a && a()
        },

        function (a) {
            $("#qijiaMessageScan").find(".message-left-owner").eq(0).animate({
                opacity: 0
            }).end().find(".message-left-message").eq(0).animate({
                opacity: 0
            }).end().find(".message-left-message").eq(1).animate({
                opacity: 0
            }).end().find(".message-left-message").eq(2).animate({
                opacity: 0
            }).end().find(".message-left-message").eq(3).animate({
                opacity: 0
            }),
            a && a()
        },

        function (a) {
            $("#messageType").find(".message-left-owner").eq(0).animate({
                opacity: 0
            }).end().find(".message-left-message").eq(0).animate({
                opacity: 0
            }).end().find(".message-left-message").eq(1).animate({
                opacity: 0
            }).end().find(".message-left-message").eq(2).animate({
                opacity: 0
            }).end().find(".message-left-message").eq(3).animate({
                opacity: 0
            }).end().find(".message-left-message").eq(4).animate({
                opacity: 0
            }).end().find(".message-left-message").eq(5).animate({
                opacity: 0
            }).end().find(".message-left-message").eq(6).animate({
                opacity: 0
            }),
            a && a()
        },


       
        function (a) {
            a && a()
        },


        function (a) {
            a && a()
        }]
    };
    a.init()
}();