
	function getTab(flag) {  
	  var elList, i, el;
	  el = document.getElementById("TabPage");
	  elList = el.getElementsByTagName("li");
	  for (i = 0; i < elList.length; i++){
		 elList[i].className ="";
	  }
	  elList[flag].className ="Selected";
	  elList[flag].blur();

	}

	function turnto(flag){
		switch(parseInt(flag)){
		case 0:
			document.all("contentText").style.display="inline";
			document.all("apiDoc").style.display="none";
			document.all("sdkDownload").style.display="none";
			document.all("discussArea").style.display="none";
			break;
		case 1:
			document.all("contentText").style.display="none";
			document.all("apiDoc").style.display="inline";
			document.all("sdkDownload").style.display="none";
			document.all("discussArea").style.display="none";
			break;
		case 2:
			document.all("contentText").style.display="none";
			document.all("apiDoc").style.display="none";
			document.all("sdkDownload").style.display="inline";
			document.all("discussArea").style.display="none";
			break;
		case 3:
			document.all("contentText").style.display="none";
			document.all("apiDoc").style.display="none";
			document.all("sdkDownload").style.display="none";
			document.all("discussArea").style.display="inline";
			break;
		default:
			break;
		}
		
		getTab(parseInt(flag));
	}