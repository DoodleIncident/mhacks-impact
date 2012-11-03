var xmlhttpMenu;
var xmlhttpRetail;

var dirPrefix = "/files/helper_files/js/";	//	use if the php files are not in the same dir as the .js and .html
var wait;	//	tracks the timeout for the menu loading gif (so that clearTimeout can be used if menu loads early)
var loadSet = false;	//	tracks if the menu loading gif has been set
var loading = '<div id="loading"><img src="/files/images/loading.gif" alt="page loading" title="page loading" />menu loading&#0133;</div>';
var loc = 'MARKETPLACE';	//	default dining location
var currentHall = '';	//	the location for the date select drop down calendar
var defaultRightCol;  // the default right col code
$(document).ready( function () { defaultRightCol = $("#rightColPic").html(); } );
$(document).ready( function () {
	$("#all_options").click( function() {
			animatedcollapse.show('diningOptions');
			$("#rightColPic", document).html(defaultRightCol);
	});
});

// array with all the dining hall info
// 	dh[n][0] = hall name for db pass
// 	dh[n][1] = hash value
// 	dh[n][2] = upper right col file | don't set for in-hall cafes
var dh = new Array(21);
	dh[0]	= new Array( "BARBOUR%20DINING%20HALL", "barbour", "rightcolpics/dining.jpg" );
	dh[1]	= new Array( "BURSLEY%20DINING%20HALL", "bursley", "rightcolpics/dining.jpg" );
	dh[2]	= new Array( "COUZENS%20DINING%20HALL", "couzens", "rightcolpics/dining.jpg" );
	dh[3]	= new Array( "EAST%20QUAD%20DINING%20HALL", "east-quad", "rightcolpics/dining.jpg" );
	dh[4]	= new Array( "LLOYD%20DINING%20HALL", "lloyd", "rightcolpics/dining.jpg" );
	dh[5]	= new Array( "MARKLEY%20DINING%20HALL", "markley", "rightcolpics/dining.jpg" );
	dh[6]	= new Array( "OXFORD", "oxford", "rightcolpics/dining.jpg" );
	dh[7]	= new Array( "SOUTH%20QUAD%20DINING%20HALL", "south-quad", "rightcolpics/dining.jpg" );
	dh[8]	= new Array( "STOCKWELL%20DINING%20HALL", "stockwell", "rightcolpics/dining.jpg" );
	dh[9]	= new Array( "WEST%20QUAD%20DINING%20HALL", "west-quad", "rightcolpics/dining.jpg" );
	dh[10]	= new Array( "MARKETPLACE", "marketplace", "flash/slideshow-marketplace.swf" );
	dh[11]	= new Array( "393", "blue-apple" );
	dh[12]	= new Array( "388", "cafe-conxion" );
	dh[13]	= new Array( "389", "ciao-down-pizzeria" );
	dh[14]	= new Array( "380", "east-quad-cafe" );
	dh[15]	= new Array( "390", "markley-hideaway" );
	dh[16]	= new Array( "392", "twigs-a-la-carte" );
	dh[17]	= new Array( "394", "north-star" );
	dh[18]	= new Array( "869", "victors" );
	dh[19]	= new Array( "871", "hours" );
	dh[20]  = new Array( "1780", "north-quad-javablu" );
	dh[21]	= new Array( "North%20Quad%20Dining%20Hall", "north-quad", "flash/slideshow-northquad-dining.swf" );
	dh[22]	= new Array( "Twigs%20at%20Oxford", "twigs-at-oxford", "rightcolpics/dining.jpg" );

function loadMenu(dateParam, locParam){
	//	show the menu container div (which is display:none on load)
	document.getElementById('menuContent').style.display='block';
	if(locParam)
		loc = locParam;
	var url = dirPrefix + "menu2xml.php?html=webmenu&location=" + loc + "&date=" + dateParam;
	xmlhttpMenu=GetXmlHttpObject();
	//	tell people with really old browsers that they can't see the menus
	if (xmlhttpMenu==null){
		alert ("Your browser does not support XMLHTTP!");
		return;
	}
	//	call function to play elevator music while the browser gets the menu from the server
	waitForIt();
	//	load right col pic if it exists
	var rightCol;
	for(var i=0; i<dh.length; i++){
		if(dh[i][0] == locParam && typeof(dh[i][2]) != 'undefined' ){
			rightCol = dh[i][2];
			i = dh.length;
		} else rightCol = false;
	}
	if(rightCol){
		$("#rightColPic").load("/files/helper_files/js/nodeLoad.php?pic=" + encodeURI(rightCol) );
	}

	xmlhttpMenu.onreadystatechange=stateChangedMenu;
	xmlhttpMenu.open("GET",url,true);
	xmlhttpMenu.send(null);
}

function loadRetail(node){
	document.getElementById('menuContent').style.display='block';
	if(node)
		loc = node;
	var retailname = "";
	for(var i in dh){if(dh[i][0] == loc) retailname = dh[i][1].replace(/-/g,"%20");}
        //alert ("value of retailname was" + retailname)
	var url = dirPrefix + "nodeLoad.php?pic=1&node=" + loc + "&hours=" + retailname;
	xmlhttpRetail=GetXmlHttpObject();
	//	tell people with really old browsers that they can't see the menus :(
	if (xmlhttpRetail==null){
		alert ("Your browser does not support XMLHTTP!");
		return;
	}
	//	call function to play elevator music while the browser gets the menu from the server
	waitForIt();
	xmlhttpRetail.onreadystatechange=stateChangedRetail;
	xmlhttpRetail.open("GET",url,true);
	xmlhttpRetail.send(null);
}

function waitForIt(){
	//	fade out the current menu w/ jQuery before loading the new one
	$("#theMenu").fadeOut(300);
	//	if the new menu has not yet loaded, then display the loading spinner and fade it in w/ jQuery
	//	for later reference, set a variable (loadSet) when the load spinner is set
//	if(xmlhttpMenu.readyState!=4){
	loadSet = false;
	wait = setTimeout('loadSet=true; $("#theMenu").html(loading); $("#theMenu").fadeIn(300);',310);
//	}

}

function stateChangedMenu()
{
if (xmlhttpMenu.readyState==4)
	{
		//	if the load spinner has been set, fade it out w/ jQuery
		//	otherwise clear the load spinner timeout so that it is not set.
		if(loadSet == true){
			setTimeout('$("#theMenu").fadeOut(300);',320);
		} else { clearTimeout(wait); }
		//	when we are sure that all current fading is over, load the new menu and fade in w/ jQuery
		setTimeout('onMenuUpdate()',640);
		setTimeout('$("#theMenu").fadeIn(300)',660);
	}
}

function stateChangedRetail()
{
if (xmlhttpRetail.readyState==4)
	{
		//	if the load spinner has been set, fade it out w/ jQuery
		//	otherwise clear the load spinner timeout so that it is not set.
		if(loadSet == true){
			setTimeout('$("#theMenu").fadeOut(300);',320);
		} else { clearTimeout(wait); }
		//	when we are sure that all current fading is over, load the new menu and fade in w/ jQuery
		setTimeout('onRetailUpdate()',640);
		setTimeout('$("#theMenu").fadeIn(300)',660);
	}
}

function onMenuUpdate(){
	var theMenu = document.getElementById("theMenu");
	theMenu.innerHTML=xmlhttpMenu.responseText;
	var dateSelectTable1 = document.getElementById("dateSelectTable").innerHTML;

	$("div.dateSelect:first").append(dateSelectTable1);
	$("div.dateSelect:not(:first)").empty();

	currentHall = $("#theMenu h1:first").html();
	document.title = currentHall + " Menu | University Housing";
	window.location.hash = "#/" + hashCheck(loc,0);
}


function onRetailUpdate(){
	//    empty the div where the new node's content will be placed
	$("#theMenu").empty();

	// seperate the node content and the right column content from the response text
	var response = xmlhttpRetail.responseText.split('<div id="right-col-content">');
	var nodeContent = response[0];
	var rightContent = response[1].substring( 0,response[1].lastIndexOf('</div>') );

	//    prep the element to go into the node content div
	var elem = document.createElement('div');
	elem.className = "block with-block-editing";
	elem.innerHTML = nodeContent;
	document.getElementById("theMenu").appendChild(elem);

	//    put the right column content in the proper div
	document.getElementById("rightColPic").innerHTML=rightContent;

	//    parse the right column content in case there is js there
	var scripts = document.getElementById("rightColPic").getElementsByTagName('script');
	for( var i=0; i<scripts.length; i++) { eval(scripts[i].innerHTML); }

	//    get title from the loaded page (h2) and make it an h1
	currentHall = $("#theMenu h2.title:first").text();
	$("#theMenu h2.title:first").replaceWith("<h1>" + currentHall + "</h1>");
	document.title = currentHall + " | University Housing";
	window.location.hash = "#/" + hashCheck(loc,0);
}

function GetXmlHttpObject()
{
if (window.XMLHttpRequest)
  {
  // code for IE7+, Firefox, Chrome, Opera, Safari
  return new XMLHttpRequest();
  }
if (window.ActiveXObject)
  {
  // code for IE6, IE5
  return new ActiveXObject("Microsoft.XMLHTTP");
  }
return null;
}

function selectDate(date){
	var items = document.getElementById('dateChoices').getElementsByTagName('td');
	for(var i=0; i<items.length; i++){
		if(items[i].className == 'selectedDate'){items[i].className = "";}
	}
	loadMenu(date);
}

function hashCheck(needle, n) {
    var key = '', a = '', b = '';
	if(n==0) { a = 0; b = 1; }
	else { a = 1; b = 0; }
	for (key in dh) {
		if (dh[key][a] == needle) {
			return dh[key][b];
		}
	}
    return false;
}

/*******************************************************
  initiate stuff
 *******************************************************/

window.onload=function(){
	animatedcollapse.addDiv('diningOptions', 'fade=0,speed=400');
	animatedcollapse.ontoggle=function($, divobj, state){ //fires each time a DIV is expanded/contracted
	//$: Access to jQuery
	//divobj: DOM reference to DIV being expanded/ collapsed. Use "divobj.id" to get its ID
	//state: "block" or "none", depending on state
	}
	animatedcollapse.init()
	var hash = window.location.hash;
	if(hash.indexOf("#/") > -1){
		hash = hash.replace("#/","");
		hash = hashCheck(hash,1);
		if(hash != false){
			animatedcollapse.toggle('diningOptions');
			if(isNaN(hash)) loadMenu('today',hash);
			else loadRetail(hash);
		}
	}
}
