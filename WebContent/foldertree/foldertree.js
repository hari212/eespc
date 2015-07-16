var ids=new Array();

function FTinit(){
if(document.getElementById){
var tids=document.getElementsByTagName('span');
for(i=0;i<tids.length;i++)if(tids[i].className=="folder")ids[ids.length]=tids[i];
for(i=0;i<ids.length;i++)ids[i].onmouseup=setstate;
//expandall(); // REMOVE THE // FROM THE BIGINNING OF THIS LINE TO HAVE THE TREE EXPANDED INITIALLY.
openById('mainID');
}}

function openById(id){
var el=document.getElementById(id);
el.parentNode.getElementsByTagName('div')[0].style.display="block";
el.getElementsByTagName('img')[0].src="minus.gif";
}

function closeById(el){
var el=document.getElementById(id);
el.parentNode.getElementsByTagName('div')[0].style.display="none";
el.getElementsByTagName('img')[0].src="plus.gif";
}

function setstate(){
for(i=0;i<ids.length;i++){
if(ids[i]==this){
var d=this.parentNode.getElementsByTagName('div')[0];
if(d.style.display=="block"){
d.style.display="none";
this.getElementsByTagName('img')[0].src="plus.gif";
}else{
d.style.display="block";
this.getElementsByTagName('img')[0].src="minus.gif";
}}}}

function expandall(){
if(document.getElementById){
for(i=0;i<ids.length;i++){
ids[i].parentNode.getElementsByTagName('div')[0].style.display="block";
ids[i].getElementsByTagName('img')[0].src="minus.gif";
}}}

function collapseall(){
if(document.getElementById){
for(i=0;i<ids.length;i++){
ids[i].parentNode.getElementsByTagName('div')[0].style.display="none";
ids[i].getElementsByTagName('img')[0].src="plus.gif";
}}}

window.onload=FTinit;