function ID()
{
alert("This is a number assigned by the facility.  E.g. # 1");

}


function doTooltip(e, msg) {
  if ( typeof Tooltip == "undefined" || !Tooltip.ready ) return;
  Tooltip.show(e, msg);
}

function hideTip() {
  if ( typeof Tooltip == "undefined" || !Tooltip.ready ) return;
  Tooltip.hide();
}

function MM_openBrWindow(theURL,winName,features) { //v2.0
  window.open(theURL,winName,features);
}

