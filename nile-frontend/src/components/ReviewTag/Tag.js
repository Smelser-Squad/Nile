import "./Tag.css";


function Tag()
{
    //hardcoded for now
    const tagArr = ["soft touch", "great length","easy to manage"];
    console.log(tagArr)
    const tagList = tagArr.map((phrase) =>
    <button>{phrase}</button> );

    return (
        <div class= "tag-container">
            {tagList}
        </div>
    );
}

export default Tag;

