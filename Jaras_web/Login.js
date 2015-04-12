function validateForm()
{
    //Check if Username is filled
    var x=document.forms["Login"]["Username"].value;
    if (x==null || x==""){
    alert("The login form is incomplete.\nPlease enter your Username!");
    return false;
    }
    
    //Check if Password is filled
    var x=document.forms["Login"]["Password"].value;
    if (x==null || x==""){
    alert("The login form is incomplete.\nPlease enter your Password!");
    return false;
    }    
}