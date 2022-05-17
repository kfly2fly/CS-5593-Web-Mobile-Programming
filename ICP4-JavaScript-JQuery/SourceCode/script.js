function getGithubInfo(user) {
   //1. Create an instance of XMLHttpRequest class and send a GET request using it.
    // The function should finally return the object(it now contains the response!)
   var xhttp =new XMLHttpRequest();
   xhttp.open('GET', "https://api.github.com/users/"+user, false); //create a get request
   xhttp.send();
   return xhttp; //return object
}

function showUser(user) {
    //2. set the contents of the h2 and the two div elements in the div '#profile' with the user content
    var image = new Image(); //create image object
    image.src = user.avatar_url; //add github profile pic to image

    var anchor = $('<a>'); //create object for anchor tag
    anchor.attr('href', user.html_url); //add the url
    anchor.text('Link to Page'); //add the txt
    anchor.addClass('Link'); //add custom CSS class

    $('#profile').children('h2').text(user.name + ' - ' +user.login); //add user info to H2 tag
    $(".avatar").append(image); //add image to avatar id
    $('.information').append(anchor) //add anchor object to information id
}

function noSuchUser(username) {
    //3. set the elements such that a suitable message is displayed
    //set h2 tag with text
    $('#profile').children('h2').text(username+'does not exist');
    var image = new Image(); //create image object
    //add image url
    image.src = 'https://www.pinclipart.com/picdir/big/562-5624234_png-red-x-red-x-clipart.png';
    image.height = 200; //customize image dimensions
    image.weight = 200;
    $(".avatar").append(image); //append the image
}

$(document).ready(function () {
    $(document).on('keypress', '#username', function (e) {
        //check if the enter(i.e return) key is pressed
        if (e.which == 13) {
            //get what the user enters
            username = $(this).val();
            //reset the text typed in the input
            $(this).val("");
            //empty out the divs when new name is entered
            $(".avatar").empty();
            $('.information').empty();
            $('#profile').children('h2').empty();
            //get the user's information and store the respsonse
            response = getGithubInfo(username);
            //if the response is successful show the user's details
            if (response.status == 200) {
                showUser(JSON.parse(response.responseText));
                //else display suitable message
            } else {
                noSuchUser(username);
            }
        }
    })
});
