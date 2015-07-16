

//One global variable to set, use true if you want the menus to reinit when the user changes text size (recommended):
resizereinit=true;

menu[1] = {
id:'menu1', //use unique quoted id (quoted) REQUIRED!!
fontsize:'100%', // express as percentage with the % sign
linkheight:22 ,  // linked horizontal cells height
hdingwidth:210 ,  // heading - non linked horizontal cells width
// Finished configuration. Use default values for all other settings for this particular menu (menu[1]) ///

menuItems:[ // REQUIRED!!
//[name, link, target, colspan, endrow?] - leave 'link' and 'target' blank to make a header
["Menu"], //create header
["Dynamic Drive", "http://www.dynamicdrive.com", ""],
["What's New", "http://www.dynamicdrive.com/new.htm",""],
["What's Hot", "http://www.dynamicdrive.com/hot.htm", ""],
["Message Forum", "http://www.dynamicdrive.com/forums", ""],
["Submit Script", "http://www.dynamicdrive.com/submitscript.htm", ""],
["Link to Us", "http://www.dynamicdrive.com/link.htm", ""],


["External Links", "", ""], //create header
["JavaScript Kit", "http://www.javascriptkit.com", "_new"],
["Freewarejava", "http://www.freewarejava.com", "_new"],
["Coding Forums", "http://www.codingforums.com", "_new"]  //no comma after last entry

]}; 

make_menus();