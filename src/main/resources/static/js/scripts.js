


function getRandom(max)
{
    return  Math.floor(Math.random() * max)
}

function colorItems()
{

    let colors = ["DarkSalmon", "DarkSlateBlue", "DarkSeaGreen",  "DarkRed"]

    let items = document.getElementsByClassName("card")
    for (const item of items)
    {
        item.style.backgroundColor += colors[getRandom(4)]
    }
}


function colorNav()
{
    const Url = window.location.href
    let links = document.getElementsByClassName("nav-link")

    for (const link of links)
    {
        if (Url.includes(link.href))
        {
            link.style.color = "white";
        }
    }

}


function onLoad()
{
    colorNav()
    colorItems()
}