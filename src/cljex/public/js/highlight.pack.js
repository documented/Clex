var hljs=new function(){var p={};var a={};function n(c){return c.replace(/&/gm,"&amp;").replace(/</gm,"&lt;").replace(/>/gm,"&gt;")}function k(s,r){if(!s){return false}for(var c=0;c<s.length;c++){if(s[c]==r){return true}}return false}function g(K,E){function L(P,O){P.sm=[];for(var N=0;N<P.c.length;N++){for(var r=0;r<O.m.length;r++){if(O.m[r].cN==P.c[N]){P.sm[P.sm.length]=O.m[r]}}}}function A(r,O){if(!O.c){return null}if(!O.sm){L(O,I)}for(var N=0;N<O.sm.length;N++){if(O.sm[N].bR.test(r)){return O.sm[N]}}return null}function x(N,r){if(D[N].e&&D[N].eR.test(r)){return 1}if(D[N].eW){var O=x(N-1,r);return O?O+1:0}return 0}function y(r,N){return N.iR&&N.iR.test(r)}function B(S,Q){var O=[];function R(T){if(!k(O,T)){O[O.length]=T}}if(S.c){for(var N=0;N<Q.m.length;N++){if(k(S.c,Q.m[N].cN)){R(Q.m[N].b)}}}var r=D.length-1;do{if(D[r].e){R(D[r].e)}r--}while(D[r+1].eW);if(S.i){R(S.i)}var P="("+O[0];for(var N=0;N<O.length;N++){P+="|"+O[N]}P+=")";return e(Q,P)}function t(O,N){var P=D[D.length-1];if(!P.t){P.t=B(P,I)}O=O.substr(N);var r=P.t.exec(O);if(!r){return[O,"",true]}if(r.index==0){return["",r[0],false]}else{return[O.substr(0,r.index),r[0],false]}}function s(Q,r){var N=I.cI?r[0].toLowerCase():r[0];for(var P in Q.keywordGroups){if(!Q.keywordGroups.hasOwnProperty(P)){continue}var O=Q.keywordGroups[P].hasOwnProperty(N);if(O){return[P,O]}}return false}function G(P,S){if(!S.k||!S.l){return n(P)}if(!S.lR){var R="("+S.l[0];for(var O=1;O<S.l.length;O++){R+="|"+S.l[O]}R+=")";S.lR=e(I,R,true)}var Q="";var T=0;S.lR.lastIndex=0;var N=S.lR.exec(P);while(N){Q+=n(P.substr(T,N.index-T));var r=s(S,N);if(r){u+=r[1];Q+='<span class="'+r[0]+'">'+n(N[0])+"</span>"}else{Q+=n(N[0])}T=S.lR.lastIndex;N=S.lR.exec(P)}Q+=n(P.substr(T,P.length-T));return Q}function M(r,O){if(O.subLanguage&&a[O.subLanguage]){var N=g(O.subLanguage,r);u+=N.keyword_count;C+=N.r;return N.value}else{return G(r,O)}}function J(O,r){var N=O.nM?"":'<span class="'+O.cN+'">';if(O.rB){c+=N;O.buffer=""}else{if(O.eB){c+=n(r)+N;O.buffer=""}else{c+=N;O.buffer=r}}D[D.length]=O}function F(R,N,S){var T=D[D.length-1];if(S){c+=M(T.buffer+R,T);return false}var O=A(N,T);if(O){c+=M(T.buffer+R,T);J(O,N);C+=O.r;return O.rB}var r=x(D.length-1,N);if(r){var Q=T.nM?"":"</span>";if(T.rE){c+=M(T.buffer+R,T)+Q}else{if(T.eE){c+=M(T.buffer+R,T)+Q+n(N)}else{c+=M(T.buffer+R+N,T)+Q}}while(r>1){Q=D[D.length-2].nM?"":"</span>";c+=Q;r--;D.length--}D.length--;D[D.length-1].buffer="";if(T.starts){for(var P=0;P<I.m.length;P++){if(I.m[P].cN==T.starts){J(I.m[P],"");break}}}return T.rE}if(y(N,T)){throw"Illegal"}}var I=p[K];var D=[I.dM];var C=0;var u=0;var c="";try{var w=0;I.dM.buffer="";do{var z=t(E,w);var v=F(z[0],z[1],z[2]);w+=z[0].length;if(!v){w+=z[1].length}}while(!z[2]);if(D.length>1){throw"Illegal"}return{r:C,keyword_count:u,value:c}}catch(H){if(H=="Illegal"){return{r:0,keyword_count:0,value:n(E)}}else{throw H}}}function h(s){var r="";for(var c=0;c<s.childNodes.length;c++){if(s.childNodes[c].nodeType==3){r+=s.childNodes[c].nodeValue}else{if(s.childNodes[c].nodeName=="BR"){r+="\n"}else{r+=h(s.childNodes[c])}}}return r}function b(t){var r=t.className.split(/\s+/);r=r.concat(t.parentNode.className.split(/\s+/));for(var c=0;c<r.length;c++){var s=r[c].replace(/^language-/,"");if(s=="no-highlight"){throw"No highlight"}if(p[s]){return s}}}function d(c){var r=[];(function(t,u){for(var s=0;s<t.childNodes.length;s++){if(t.childNodes[s].nodeType==3){u+=t.childNodes[s].nodeValue.length}else{if(t.childNodes[s].nodeName=="BR"){u+=1}else{r.push({event:"start",offset:u,node:t.childNodes[s]});u=arguments.callee(t.childNodes[s],u);r.push({event:"stop",offset:u,node:t.childNodes[s]})}}}return u})(c,0);return r}function m(z,A,y){var s=0;var x="";var u=[];function v(){if(z.length&&A.length){if(z[0].offset!=A[0].offset){return(z[0].offset<A[0].offset)?z:A}else{return(z[0].event=="start"&&A[0].event=="stop")?A:z}}else{return z.length?z:A}}function t(D){var E="<"+D.nodeName.toLowerCase();for(var C=0;C<D.attributes.length;C++){E+=" "+D.attributes[C].nodeName.toLowerCase()+'="'+n(D.attributes[C].nodeValue)+'"'}return E+">"}function B(C){return"</"+C.nodeName.toLowerCase()+">"}while(z.length||A.length){var w=v().splice(0,1)[0];x+=n(y.substr(s,w.offset-s));s=w.offset;if(w.event=="start"){x+=t(w.node);u.push(w.node)}else{if(w.event=="stop"){var r=u.length;do{r--;var c=u[r];x+=B(c)}while(c!=w.node);u.splice(r,1);while(r<u.length){x+=t(u[r]);r++}}}}x+=y.substr(s);return x}function q(y,C){try{var F=h(y);var v=b(y)}catch(z){if(z=="No highlight"){return}}if(v){var B=g(v,F).value}else{var D=0;for(var E in a){if(!a.hasOwnProperty(E)){continue}var t=g(E,F);var c=t.keyword_count+t.r;if(c>D){D=c;var B=t.value;v=E}}}if(B){if(C){B=B.replace(/^(\t+)/gm,function(r,I,H,G){return I.replace(/\t/g,C)})}var x=y.className;if(!x.match(v)){x+=" "+v}var s=d(y);if(s.length){var u=document.createElement("pre");u.innerHTML=B;B=m(s,d(u),F)}var A=document.createElement("div");A.innerHTML='<pre><code class="'+x+'">'+B+"</code></pre>";var w=y.parentNode.parentNode;w.replaceChild(A.firstChild,y.parentNode)}}function e(s,r,c){var t="m"+(s.cI?"i":"")+(c?"g":"");return new RegExp(r,t)}function j(){for(var r in p){if(!p.hasOwnProperty(r)){continue}var s=p[r];for(var c=0;c<s.m.length;c++){if(s.m[c].b){s.m[c].bR=e(s,"^"+s.m[c].b)}if(s.m[c].e){s.m[c].eR=e(s,"^"+s.m[c].e)}if(s.m[c].i){s.m[c].iR=e(s,"^(?:"+s.m[c].i+")")}s.dM.iR=e(s,"^(?:"+s.dM.i+")");if(s.m[c].r==undefined){s.m[c].r=1}}}}function f(){function s(v){if(!v.keywordGroups){for(var u in v.k){if(!v.k.hasOwnProperty(u)){continue}if(v.k[u] instanceof Object){v.keywordGroups=v.k}else{v.keywordGroups={keyword:v.k}}break}}}for(var r in p){if(!p.hasOwnProperty(r)){continue}var t=p[r];s(t.dM);for(var c=0;c<t.m.length;c++){s(t.m[c])}}}function i(r){for(var c=0;c<r.childNodes.length;c++){node=r.childNodes[c];if(node.nodeName=="CODE"){return node}if(!(node.nodeType==3&&node.nodeValue.match(/\s+/))){return null}}}function l(){if(l.called){return}l.called=true;j();f();if(arguments.length){for(var c=0;c<arguments.length;c++){if(p[arguments[c]]){a[arguments[c]]=p[arguments[c]]}}}else{a=p}var s=document.getElementsByTagName("pre");for(var c=0;c<s.length;c++){var r=i(s[c]);if(r){q(r,hljs.tabReplace)}}}function o(){var c=arguments;var r=function(){l.apply(null,c)};if(window.addEventListener){window.addEventListener("DOMContentLoaded",r,false);window.addEventListener("load",r,false)}else{if(window.attachEvent){window.attachEvent("onload",r)}else{window.onload=r}}}this.LANGUAGES=p;this.initHighlightingOnLoad=o;this.highlightBlock=q;this.initHighlighting=l;this.IR="[a-zA-Z][a-zA-Z0-9_]*";this.UIR="[a-zA-Z_][a-zA-Z0-9_]*";this.NR="\\b\\d+(\\.\\d+)?";this.CNR="\\b(0x[A-Za-z0-9]+|\\d+(\\.\\d+)?)";this.RSR="!|!=|!==|%|%=|&|&&|&=|\\*|\\*=|\\+|\\+=|,|\\.|-|-=|/|/=|:|;|<|<<|<<=|<=|=|==|===|>|>=|>>|>>=|>>>|>>>=|\\?|\\[|\\{|\\(|\\^|\\^=|\\||\\|=|\\|\\||~";this.ASM={cN:"string",b:"'",e:"'",i:"\\n",c:["escape"],r:0};this.QSM={cN:"string",b:'"',e:'"',i:"\\n",c:["escape"],r:0};this.BE={cN:"escape",b:"\\\\.",e:"^",nM:true,r:0};this.CLCM={cN:"comment",b:"//",e:"$",r:0};this.CBLCLM={cN:"comment",b:"/\\*",e:"\\*/"};this.HCM={cN:"comment",b:"#",e:"$"};this.CNM={cN:"number",b:this.CNR,e:"^",r:0}}();var initHighlightingOnLoad=hljs.initHighlightingOnLoad;hljs.LANGUAGES.bash=function(){var a={"true":1,"false":1};return{dM:{l:[hljs.IR],c:["string","shebang","comment","number","test_condition","string","variable"],k:{keyword:{"if":1,then:1,"else":1,fi:1,"for":1,"break":1,"continue":1,"while":1,"in":1,"do":1,done:1,echo:1,exit:1,"return":1,set:1,declare:1},literal:a}},cI:false,m:[{cN:"shebang",b:"(#!\\/bin\\/bash)|(#!\\/bin\\/sh)",e:"^",r:10},hljs.HCM,{cN:"test_condition",b:"\\[ ",e:" \\]",c:["string","variable","number"],l:[hljs.IR],k:{literal:a},r:0},{cN:"test_condition",b:"\\[\\[ ",e:" \\]\\]",c:["string","variable","number"],l:[hljs.IR],k:{literal:a}},{cN:"variable",b:"\\$([a-zA-Z0-9_]+)\\b",e:"^"},{cN:"variable",b:"\\$\\{(([^}])|(\\\\}))+\\}",e:"^",c:["number"]},{cN:"string",b:'"',e:'"',i:"\\n",c:["escape","variable"],r:0},{cN:"string",b:'"',e:'"',i:"\\n",c:["escape","variable"],r:0},hljs.BE,hljs.CNM,{cN:"comment",b:"\\/\\/",e:"$",i:"."}]}}();hljs.XML_COMMENT={cN:"comment",b:"<!--",e:"-->"};hljs.XML_ATTR={cN:"attribute",b:"\\s[a-zA-Z\\:-]+=",e:"^",c:["value"]};hljs.XML_VALUE_QUOT={cN:"value",b:'"',e:'"'};hljs.XML_VALUE_APOS={cN:"value",b:"'",e:"'"};hljs.LANGUAGES.xml={dM:{c:["pi","comment","cdata","tag"]},cI:true,m:[{cN:"pi",b:"<\\?",e:"\\?>",r:10},hljs.XML_COMMENT,{cN:"cdata",b:"<\\!\\[CDATA\\[",e:"\\]\\]>"},{cN:"tag",b:"</?",e:">",c:["title","tag_internal"],r:1.5},{cN:"title",b:"[A-Za-z:_][A-Za-z0-9\\._:-]+",e:"^",r:0},{cN:"tag_internal",b:"^",eW:true,nM:true,c:["attribute"],r:0,i:"[\\+\\.]"},hljs.XML_ATTR,hljs.XML_VALUE_QUOT,hljs.XML_VALUE_APOS]};hljs.HTML_TAGS={code:1,kbd:1,font:1,noscript:1,style:1,img:1,title:1,menu:1,tt:1,tr:1,param:1,li:1,tfoot:1,th:1,input:1,td:1,dl:1,blockquote:1,fieldset:1,big:1,dd:1,abbr:1,optgroup:1,dt:1,button:1,isindex:1,p:1,small:1,div:1,dir:1,em:1,frame:1,meta:1,sub:1,bdo:1,label:1,acronym:1,sup:1,body:1,xml:1,basefont:1,base:1,br:1,address:1,strong:1,legend:1,ol:1,script:1,caption:1,s:1,col:1,h2:1,h3:1,h1:1,h6:1,h4:1,h5:1,table:1,select:1,noframes:1,span:1,area:1,dfn:1,strike:1,cite:1,thead:1,head:1,option:1,form:1,hr:1,"var":1,link:1,b:1,colgroup:1,ul:1,applet:1,del:1,iframe:1,pre:1,frameset:1,ins:1,tbody:1,html:1,samp:1,map:1,object:1,a:1,xmlns:1,center:1,textarea:1,i:1,q:1,u:1};hljs.HTML_DOCTYPE={cN:"doctype",b:"<!DOCTYPE",e:">",r:10};hljs.HTML_ATTR={cN:"attribute",b:"\\s[a-zA-Z\\:-]+=",e:"^",c:["value"]};hljs.HTML_SHORT_ATTR={cN:"attribute",b:" [a-zA-Z]+",e:"^"};hljs.HTML_VALUE={cN:"value",b:"[a-zA-Z0-9]+",e:"^"};hljs.LANGUAGES.html={dM:{c:["tag","comment","doctype","vbscript"]},cI:true,m:[hljs.XML_COMMENT,hljs.HTML_DOCTYPE,{cN:"tag",l:[hljs.IR],k:hljs.HTML_TAGS,b:"<style",e:">",c:["attribute"],i:"[\\+\\.]",starts:"css"},{cN:"tag",l:[hljs.IR],k:hljs.HTML_TAGS,b:"<script",e:">",c:["attribute"],i:"[\\+\\.]",starts:"javascript"},{cN:"tag",l:[hljs.IR],k:hljs.HTML_TAGS,b:"<[A-Za-z/]",e:">",c:["attribute"],i:"[\\+\\.]"},{cN:"css",e:"</style>",rE:true,subLanguage:"css"},{cN:"javascript",e:"<\/script>",rE:true,subLanguage:"javascript"},hljs.HTML_ATTR,hljs.HTML_SHORT_ATTR,hljs.XML_VALUE_QUOT,hljs.XML_VALUE_APOS,hljs.HTML_VALUE,{cN:"vbscript",b:"<%",e:"%>",subLanguage:"vbscript"}]};hljs.LANGUAGES.lisp=function(){var a="[a-zA-Z_\\-\\+\\*\\/\\<\\=\\>\\&\\#][a-zA-Z0-9_\\-\\+\\*\\/\\<\\=\\>\\&\\#]*";var b="(\\-|\\+)?\\d+(\\.\\d+|\\/\\d+)?((d|e|f|l|s)(\\+|\\-)?\\d+)?";return{cI:true,dM:{l:[a],c:["literal","number","string","comment","quoted","list"],i:"[^\\s]"},m:[hljs.QSM,{cN:"number",b:b,e:"^"},{cN:"number",b:"#b[0-1]+(/[0-1]+)?",e:"^"},{cN:"number",b:"#o[0-7]+(/[0-7]+)?",e:"^"},{cN:"number",b:"#x[0-9a-f]+(/[0-9a-f]+)?",e:"^"},{cN:"number",b:"#c\\("+b+" +"+b,e:"\\)"},{cN:"comment",b:";",e:"$"},{cN:"quoted",b:"['`]\\(",e:"\\)",c:["number","string","variable","keyword","quoted_list"]},{cN:"quoted",b:"\\(quote ",e:"\\)",c:["number","string","variable","keyword","quoted_list"],l:[a],k:{title:{quote:1}}},{cN:"quoted_list",b:"\\(",e:"\\)",c:["quoted_list","literal","number","string"]},{cN:"list",b:"\\(",e:"\\)",c:["title","body"]},{cN:"title",b:a,e:"^",eW:true},{cN:"body",b:"^",eW:true,eE:true,c:["quoted","list","literal","number","string","comment","variable","keyword"]},{cN:"keyword",b:"[:&]"+a,e:"^"},{cN:"variable",b:"\\*",e:"\\*"},{cN:"literal",b:"\\b(t{1}|nil)\\b",e:"^"}]}}();hljs.LANGUAGES.java={dM:{l:[hljs.UIR],c:["javadoc","comment","string","class","number","annotation"],k:{"false":1,"synchronized":1,"int":1,"abstract":1,"float":1,"private":1,"char":1,"interface":1,"boolean":1,"static":1,"null":1,"if":1,"const":1,"for":1,"true":1,"while":1,"long":1,"throw":1,strictfp:1,"finally":1,"protected":1,"extends":1,"import":1,"native":1,"final":1,"implements":1,"return":1,"void":1,"enum":1,"else":1,"break":1,"transient":1,"new":1,"catch":1,"instanceof":1,"byte":1,"super":1,"class":1,"volatile":1,"case":1,assert:1,"short":1,"package":1,"default":1,"double":1,"public":1,"try":1,"this":1,"switch":1,"continue":1,"throws":1}},m:[{cN:"class",l:[hljs.UIR],b:"(class |interface )",e:"{",i:":",k:{"class":1,"interface":1},c:["inheritance","title"]},{cN:"inheritance",b:"(implements|extends)",e:"^",nM:true,l:[hljs.IR],k:{"extends":1,"implements":1},r:10},{cN:"title",b:hljs.UIR,e:"^"},{cN:"params",b:"\\(",e:"\\)",c:["string","annotation"]},hljs.CNM,hljs.ASM,hljs.QSM,hljs.BE,hljs.CLCM,{cN:"javadoc",b:"/\\*\\*",e:"\\*/",c:["javadoctag"],r:10},{cN:"javadoctag",b:"@[A-Za-z]+",e:"^"},hljs.CBLCLM,{cN:"annotation",b:"@[A-Za-z]+",e:"^"}]};