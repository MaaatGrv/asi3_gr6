!function(t,e,o,n){"use strict";e=void 0!==e&&e.Math==Math?e:"undefined"!=typeof self&&self.Math==Math?self:Function("return this")(),t.fn.popup=function(i){var r,a=t(this),s=t(o),p=t(e),l=t("body"),u=a.selector||"",c=(new Date).getTime(),d=[],f=arguments[0],g="string"==typeof f,h=[].slice.call(arguments,1);return a.each(function(){var a,m,v,b,w,y,C=t.isPlainObject(i)?t.extend(!0,{},t.fn.popup.settings,i):t.extend({},t.fn.popup.settings),T=C.selector,P=C.className,x=C.error,k=C.metadata,S=C.namespace,E="."+C.namespace,O="module-"+S,A=t(this),D=t(C.context),j=t(C.scrollContext),F=t(C.boundary),R=C.target?t(C.target):A,H=0,N=!1,V=!1,M=this,W=A.data(O);y={initialize:function(){y.debug("Initializing",A),y.createID(),y.bind.events(),!y.exists()&&C.preserve&&y.create(),C.observeChanges&&y.observeChanges(),y.instantiate()},instantiate:function(){y.verbose("Storing instance",y),W=y,A.data(O,W)},observeChanges:function(){"MutationObserver"in e&&((v=new MutationObserver(y.event.documentChanged)).observe(o,{childList:!0,subtree:!0}),y.debug("Setting up mutation observer",v))},refresh:function(){C.popup?a=t(C.popup).eq(0):C.inline&&(a=R.nextAll(T.popup).eq(0),C.popup=a),C.popup?(a.addClass(P.loading),m=y.get.offsetParent(),a.removeClass(P.loading),C.movePopup&&y.has.popup()&&y.get.offsetParent(a)[0]!==m[0]&&(y.debug("Moving popup to the same offset parent as target"),a.detach().appendTo(m))):m=C.inline?y.get.offsetParent(R):y.has.popup()?y.get.offsetParent(a):l,m.is("html")&&m[0]!==l[0]&&(y.debug("Setting page as offset parent"),m=l),y.get.variation()&&y.set.variation()},reposition:function(){y.refresh(),y.set.position()},destroy:function(){y.debug("Destroying previous module"),v&&v.disconnect(),a&&!C.preserve&&y.removePopup(),clearTimeout(y.hideTimer),clearTimeout(y.showTimer),y.unbind.close(),y.unbind.events(),A.removeData(O)},event:{start:function(e){var o=t.isPlainObject(C.delay)?C.delay.show:C.delay;clearTimeout(y.hideTimer),V||(y.showTimer=setTimeout(y.show,o))},end:function(){var e=t.isPlainObject(C.delay)?C.delay.hide:C.delay;clearTimeout(y.showTimer),y.hideTimer=setTimeout(y.hide,e)},touchstart:function(t){V=!0,y.show()},resize:function(){y.is.visible()&&y.set.position()},documentChanged:function(e){[].forEach.call(e,function(e){e.removedNodes&&[].forEach.call(e.removedNodes,function(e){(e==M||t(e).find(M).length>0)&&(y.debug("Element removed from DOM, tearing down events"),y.destroy())})})},hideGracefully:function(e){var n=t(e.target),i=t.contains(o.documentElement,e.target),r=n.closest(T.popup).length>0;e&&!r&&i?(y.debug("Click occurred outside popup hiding popup"),y.hide()):y.debug("Click was inside popup, keeping popup open")}},create:function(){var e=y.get.html(),o=y.get.title(),n=y.get.content();e||n||o?(y.debug("Creating pop-up html"),e||(e=C.templates.popup({title:o,content:n})),a=t("<div/>").addClass(P.popup).data(k.activator,A).html(e),C.inline?(y.verbose("Inserting popup element inline",a),a.insertAfter(A)):(y.verbose("Appending popup element to body",a),a.appendTo(D)),y.refresh(),y.set.variation(),C.hoverable&&y.bind.popup(),C.onCreate.call(a,M)):0!==R.next(T.popup).length?(y.verbose("Pre-existing popup found"),C.inline=!0,C.popup=R.next(T.popup).data(k.activator,A),y.refresh(),C.hoverable&&y.bind.popup()):C.popup?(t(C.popup).data(k.activator,A),y.verbose("Used popup specified in settings"),y.refresh(),C.hoverable&&y.bind.popup()):y.debug("No content specified skipping display",M)},createID:function(){w=(Math.random().toString(16)+"000000000").substr(2,8),b="."+w,y.verbose("Creating unique id for element",w)},toggle:function(){y.debug("Toggling pop-up"),y.is.hidden()?(y.debug("Popup is hidden, showing pop-up"),y.unbind.close(),y.show()):(y.debug("Popup is visible, hiding pop-up"),y.hide())},show:function(t){if(t=t||function(){},y.debug("Showing pop-up",C.transition),y.is.hidden()&&(!y.is.active()||!y.is.dropdown())){if(y.exists()||y.create(),!1===C.onShow.call(a,M))return void y.debug("onShow callback returned false, cancelling popup animation");C.preserve||C.popup||y.refresh(),a&&y.set.position()&&(y.save.conditions(),C.exclusive&&y.hideAll(),y.animate.show(t))}},hide:function(t){if(t=t||function(){},y.is.visible()||y.is.animating()){if(!1===C.onHide.call(a,M))return void y.debug("onHide callback returned false, cancelling popup animation");y.remove.visible(),y.unbind.close(),y.restore.conditions(),y.animate.hide(t)}},hideAll:function(){t(T.popup).filter("."+P.popupVisible).each(function(){t(this).data(k.activator).popup("hide")})},exists:function(){return!!a&&(C.inline||C.popup?y.has.popup():a.closest(D).length>=1)},removePopup:function(){y.has.popup()&&!C.popup&&(y.debug("Removing popup",a),a.remove(),a=n,C.onRemove.call(a,M))},save:{conditions:function(){y.cache={title:A.attr("title")},y.cache.title&&A.removeAttr("title"),y.verbose("Saving original attributes",y.cache.title)}},restore:{conditions:function(){return y.cache&&y.cache.title&&(A.attr("title",y.cache.title),y.verbose("Restoring original attributes",y.cache.title)),!0}},supports:{svg:function(){return"undefined"==typeof SVGGraphicsElement}},animate:{show:function(e){e=t.isFunction(e)?e:function(){},C.transition&&t.fn.transition!==n&&A.transition("is supported")?(y.set.visible(),a.transition({animation:C.transition+" in",queue:!1,debug:C.debug,verbose:C.verbose,duration:C.duration,onComplete:function(){y.bind.close(),e.call(a,M),C.onVisible.call(a,M)}})):y.error(x.noTransition)},hide:function(e){e=t.isFunction(e)?e:function(){},y.debug("Hiding pop-up"),!1!==C.onHide.call(a,M)?C.transition&&t.fn.transition!==n&&A.transition("is supported")?a.transition({animation:C.transition+" out",queue:!1,duration:C.duration,debug:C.debug,verbose:C.verbose,onComplete:function(){y.reset(),e.call(a,M),C.onHidden.call(a,M)}}):y.error(x.noTransition):y.debug("onHide callback returned false, cancelling popup animation")}},change:{content:function(t){a.html(t)}},get:{html:function(){return A.removeData(k.html),A.data(k.html)||C.html},title:function(){return A.removeData(k.title),A.data(k.title)||C.title},content:function(){return A.removeData(k.content),A.data(k.content)||A.attr("title")||C.content},variation:function(){return A.removeData(k.variation),A.data(k.variation)||C.variation},popup:function(){return a},popupOffset:function(){return a.offset()},calculations:function(){var t,o=R[0],n=F[0]==e,i=C.inline||C.popup&&C.movePopup?R.position():R.offset(),r=n?{top:0,left:0}:F.offset(),s={},l=n?{top:p.scrollTop(),left:p.scrollLeft()}:{top:0,left:0};return s={target:{element:R[0],width:R.outerWidth(),height:R.outerHeight(),top:i.top,left:i.left,margin:{}},popup:{width:a.outerWidth(),height:a.outerHeight()},parent:{width:m.outerWidth(),height:m.outerHeight()},screen:{top:r.top,left:r.left,scroll:{top:l.top,left:l.left},width:F.width(),height:F.height()}},C.setFluidWidth&&y.is.fluid()&&(s.container={width:a.parent().outerWidth()},s.popup.width=s.container.width),s.target.margin.top=C.inline?parseInt(e.getComputedStyle(o).getPropertyValue("margin-top"),10):0,s.target.margin.left=C.inline?y.is.rtl()?parseInt(e.getComputedStyle(o).getPropertyValue("margin-right"),10):parseInt(e.getComputedStyle(o).getPropertyValue("margin-left"),10):0,t=s.screen,s.boundary={top:t.top+t.scroll.top,bottom:t.top+t.scroll.top+t.height,left:t.left+t.scroll.left,right:t.left+t.scroll.left+t.width},s},id:function(){return w},startEvent:function(){return"hover"==C.on?"mouseenter":"focus"==C.on&&"focus"},scrollEvent:function(){return"scroll"},endEvent:function(){return"hover"==C.on?"mouseleave":"focus"==C.on&&"blur"},distanceFromBoundary:function(t,e){var o,n,i={};return o=(e=e||y.get.calculations()).popup,n=e.boundary,t&&(i={top:t.top-n.top,left:t.left-n.left,right:n.right-(t.left+o.width),bottom:n.bottom-(t.top+o.height)},y.verbose("Distance from boundaries determined",t,i)),i},offsetParent:function(e){var o=(e!==n?e[0]:R[0]).parentNode,i=t(o);if(o)for(var r="none"===i.css("transform"),a="static"===i.css("position"),s=i.is("html");o&&!s&&a&&r;)o=o.parentNode,r="none"===(i=t(o)).css("transform"),a="static"===i.css("position"),s=i.is("html");return i&&i.length>0?i:t()},positions:function(){return{"top left":!1,"top center":!1,"top right":!1,"bottom left":!1,"bottom center":!1,"bottom right":!1,"left center":!1,"right center":!1}},nextPosition:function(t){var e=t.split(" "),o=e[0],n=e[1],i="top"==o||"bottom"==o,r=!1,a=!1,s=!1;return N||(y.verbose("All available positions available"),N=y.get.positions()),y.debug("Recording last position tried",t),N[t]=!0,"opposite"===C.prefer&&(s=(s=[{top:"bottom",bottom:"top",left:"right",right:"left"}[o],n]).join(" "),r=!0===N[s],y.debug("Trying opposite strategy",s)),"adjacent"===C.prefer&&i&&(s=(s=[o,{left:"center",center:"right",right:"left"}[n]]).join(" "),a=!0===N[s],y.debug("Trying adjacent strategy",s)),(a||r)&&(y.debug("Using backup position",s),s={"top left":"top center","top center":"top right","top right":"right center","right center":"bottom right","bottom right":"bottom center","bottom center":"bottom left","bottom left":"left center","left center":"top left"}[t]),s}},set:{position:function(t,e){if(0!==R.length&&0!==a.length){var o,i,r,s,p,l,u,c;if(e=e||y.get.calculations(),t=t||A.data(k.position)||C.position,o=A.data(k.offset)||C.offset,i=C.distanceAway,r=e.target,s=e.popup,p=e.parent,0===r.width&&0===r.height&&!y.is.svg(r.element))return y.debug("Popup target is hidden, no action taken"),!1;switch(C.inline&&(y.debug("Adding margin to calculation",r.margin),"left center"==t||"right center"==t?(o+=r.margin.top,i+=-r.margin.left):"top left"==t||"top center"==t||"top right"==t?(o+=r.margin.left,i-=r.margin.top):(o+=r.margin.left,i+=r.margin.top)),y.debug("Determining popup position from calculations",t,e),y.is.rtl()&&(t=t.replace(/left|right/g,function(t){return"left"==t?"right":"left"}),y.debug("RTL: Popup position updated",t)),H==C.maxSearchDepth&&"string"==typeof C.lastResort&&(t=C.lastResort),t){case"top left":l={top:"auto",bottom:p.height-r.top+i,left:r.left+o,right:"auto"};break;case"top center":l={bottom:p.height-r.top+i,left:r.left+r.width/2-s.width/2+o,top:"auto",right:"auto"};break;case"top right":l={bottom:p.height-r.top+i,right:p.width-r.left-r.width-o,top:"auto",left:"auto"};break;case"left center":l={top:r.top+r.height/2-s.height/2+o,right:p.width-r.left+i,left:"auto",bottom:"auto"};break;case"right center":l={top:r.top+r.height/2-s.height/2+o,left:r.left+r.width+i,bottom:"auto",right:"auto"};break;case"bottom left":l={top:r.top+r.height+i,left:r.left+o,bottom:"auto",right:"auto"};break;case"bottom center":l={top:r.top+r.height+i,left:r.left+r.width/2-s.width/2+o,bottom:"auto",right:"auto"};break;case"bottom right":l={top:r.top+r.height+i,right:p.width-r.left-r.width-o,left:"auto",bottom:"auto"}}if(l===n&&y.error(x.invalidPosition,t),y.debug("Calculated popup positioning values",l),a.css(l).removeClass(P.position).addClass(t).addClass(P.loading),u=y.get.popupOffset(),c=y.get.distanceFromBoundary(u,e),y.is.offstage(c,t)){if(y.debug("Position is outside viewport",t),H<C.maxSearchDepth)return H++,t=y.get.nextPosition(t),y.debug("Trying new position",t),!!a&&y.set.position(t,e);if(!C.lastResort)return y.debug("Popup could not find a position to display",a),y.error(x.cannotPlace,M),y.remove.attempts(),y.remove.loading(),y.reset(),C.onUnplaceable.call(a,M),!1;y.debug("No position found, showing with last position")}return y.debug("Position is on stage",t),y.remove.attempts(),y.remove.loading(),C.setFluidWidth&&y.is.fluid()&&y.set.fluidWidth(e),!0}y.error(x.notFound)},fluidWidth:function(t){t=t||y.get.calculations(),y.debug("Automatically setting element width to parent width",t.parent.width),a.css("width",t.container.width)},variation:function(t){(t=t||y.get.variation())&&y.has.popup()&&(y.verbose("Adding variation to popup",t),a.addClass(t))},visible:function(){A.addClass(P.visible)}},remove:{loading:function(){a.removeClass(P.loading)},variation:function(t){(t=t||y.get.variation())&&(y.verbose("Removing variation",t),a.removeClass(t))},visible:function(){A.removeClass(P.visible)},attempts:function(){y.verbose("Resetting all searched positions"),H=0,N=!1}},bind:{events:function(){y.debug("Binding popup events to module"),"click"==C.on&&A.on("click"+E,y.toggle),"hover"==C.on&&A.on("touchstart"+E,y.event.touchstart),y.get.startEvent()&&A.on(y.get.startEvent()+E,y.event.start).on(y.get.endEvent()+E,y.event.end),C.target&&y.debug("Target set to element",R),p.on("resize"+b,y.event.resize)},popup:function(){y.verbose("Allowing hover events on popup to prevent closing"),a&&y.has.popup()&&a.on("mouseenter"+E,y.event.start).on("mouseleave"+E,y.event.end)},close:function(){(!0===C.hideOnScroll||"auto"==C.hideOnScroll&&"click"!=C.on)&&y.bind.closeOnScroll(),"hover"==C.on&&V&&y.bind.touchClose(),"click"==C.on&&C.closable&&y.bind.clickaway()},closeOnScroll:function(){y.verbose("Binding scroll close event to document"),j.one(y.get.scrollEvent()+b,y.event.hideGracefully)},touchClose:function(){y.verbose("Binding popup touchclose event to document"),s.on("touchstart"+b,function(t){y.verbose("Touched away from popup"),y.event.hideGracefully.call(M,t)})},clickaway:function(){y.verbose("Binding popup close event to document"),s.on("click"+b,function(t){y.verbose("Clicked away from popup"),y.event.hideGracefully.call(M,t)})}},unbind:{events:function(){p.off(b),A.off(E)},close:function(){s.off(b),j.off(b)}},has:{popup:function(){return a&&a.length>0}},is:{offstage:function(e,o){var n=[];return t.each(e,function(t,e){e<-C.jitter&&(y.debug("Position exceeds allowable distance from edge",t,e,o),n.push(t))}),n.length>0},svg:function(t){return y.supports.svg()&&t instanceof SVGGraphicsElement},active:function(){return A.hasClass(P.active)},animating:function(){return a!==n&&a.hasClass(P.animating)},fluid:function(){return a!==n&&a.hasClass(P.fluid)},visible:function(){return a!==n&&a.hasClass(P.popupVisible)},dropdown:function(){return A.hasClass(P.dropdown)},hidden:function(){return!y.is.visible()},rtl:function(){return"rtl"==A.css("direction")}},reset:function(){y.remove.visible(),C.preserve?t.fn.transition!==n&&a.transition("remove transition"):y.removePopup()},setting:function(e,o){if(t.isPlainObject(e))t.extend(!0,C,e);else{if(o===n)return C[e];C[e]=o}},internal:function(e,o){if(t.isPlainObject(e))t.extend(!0,y,e);else{if(o===n)return y[e];y[e]=o}},debug:function(){!C.silent&&C.debug&&(C.performance?y.performance.log(arguments):(y.debug=Function.prototype.bind.call(console.info,console,C.name+":"),y.debug.apply(console,arguments)))},verbose:function(){!C.silent&&C.verbose&&C.debug&&(C.performance?y.performance.log(arguments):(y.verbose=Function.prototype.bind.call(console.info,console,C.name+":"),y.verbose.apply(console,arguments)))},error:function(){C.silent||(y.error=Function.prototype.bind.call(console.error,console,C.name+":"),y.error.apply(console,arguments))},performance:{log:function(t){var e,o;C.performance&&(o=(e=(new Date).getTime())-(c||e),c=e,d.push({Name:t[0],Arguments:[].slice.call(t,1)||"",Element:M,"Execution Time":o})),clearTimeout(y.performance.timer),y.performance.timer=setTimeout(y.performance.display,500)},display:function(){var e=C.name+":",o=0;c=!1,clearTimeout(y.performance.timer),t.each(d,function(t,e){o+=e["Execution Time"]}),e+=" "+o+"ms",u&&(e+=" '"+u+"'"),(console.group!==n||console.table!==n)&&d.length>0&&(console.groupCollapsed(e),console.table?console.table(d):t.each(d,function(t,e){console.log(e.Name+": "+e["Execution Time"]+"ms")}),console.groupEnd()),d=[]}},invoke:function(e,o,i){var a,s,p,l=W;return o=o||h,i=M||i,"string"==typeof e&&l!==n&&(e=e.split(/[\. ]/),a=e.length-1,t.each(e,function(o,i){var r=o!=a?i+e[o+1].charAt(0).toUpperCase()+e[o+1].slice(1):e;if(t.isPlainObject(l[r])&&o!=a)l=l[r];else{if(l[r]!==n)return s=l[r],!1;if(!t.isPlainObject(l[i])||o==a)return l[i]!==n&&(s=l[i],!1);l=l[i]}})),t.isFunction(s)?p=s.apply(i,o):s!==n&&(p=s),t.isArray(r)?r.push(p):r!==n?r=[r,p]:p!==n&&(r=p),s}},g?(W===n&&y.initialize(),y.invoke(f)):(W!==n&&W.invoke("destroy"),y.initialize())}),r!==n?r:this},t.fn.popup.settings={name:"Popup",silent:!1,debug:!1,verbose:!1,performance:!0,namespace:"popup",observeChanges:!0,onCreate:function(){},onRemove:function(){},onShow:function(){},onVisible:function(){},onHide:function(){},onUnplaceable:function(){},onHidden:function(){},on:"hover",boundary:e,addTouchEvents:!0,position:"top left",variation:"",movePopup:!0,target:!1,popup:!1,inline:!1,preserve:!1,hoverable:!1,content:!1,html:!1,title:!1,closable:!0,hideOnScroll:"auto",exclusive:!1,context:"body",scrollContext:e,prefer:"opposite",lastResort:!1,delay:{show:50,hide:70},setFluidWidth:!0,duration:200,transition:"scale",distanceAway:0,jitter:2,offset:0,maxSearchDepth:15,error:{invalidPosition:"The position you specified is not a valid position",cannotPlace:"Popup does not fit within the boundaries of the viewport",method:"The method you called is not defined.",noTransition:"This module requires ui transitions <https://github.com/Semantic-Org/UI-Transition>",notFound:"The target or popup you specified does not exist on the page"},metadata:{activator:"activator",content:"content",html:"html",offset:"offset",position:"position",title:"title",variation:"variation"},className:{active:"active",animating:"animating",dropdown:"dropdown",fluid:"fluid",loading:"loading",popup:"ui popup",position:"top left center bottom right",visible:"visible",popupVisible:"visible"},selector:{popup:".ui.popup"},templates:{escape:function(t){var e={"&":"&amp;","<":"&lt;",">":"&gt;",'"':"&quot;","'":"&#x27;","`":"&#x60;"};return/[&<>"'`]/.test(t)?t.replace(/[&<>"'`]/g,function(t){return e[t]}):t},popup:function(e){var o="",i=t.fn.popup.settings.templates.escape;return typeof e!==n&&(typeof e.title!==n&&e.title&&(e.title=i(e.title),o+='<div class="header">'+e.title+"</div>"),typeof e.content!==n&&e.content&&(e.content=i(e.content),o+='<div class="content">'+e.content+"</div>")),o}}}}(jQuery,window,document);