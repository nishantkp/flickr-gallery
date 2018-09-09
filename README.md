# flickr-gallary

A gallary App with flicklr API

## Task Overview
To design a mobile app which shows list of images from `query` entered by user.

## Helpers :smiley:
- MVP Architecture + DataBinding
- DiffUtils.Callbak
- RxJava/ RxAndroid
- Picasso
- Retrofit
- GsonConvertor _`(Custom convertor as well)`_

## API Helper

_Flickr endpoint for image search,_
<br>`https://api.flickr.com/services/rest/?api_key=949e98778755d1982f537d56236bbb42&amp`</br>
_method,_
<br>`flickr.photos.search`</br>
_Query parameters_
<br>`format=jsonl`</br> `nojsoncallback=1 text={USER_SEARCH_QUERY}`

_Size of images is retrieved from,_
_method,_
<br>`method=flickr.photos.getSizes`</br>

for more detail, check out this Flickr [link](https://www.flickr.com/services/api/).l

### Teade-offs
_Not in app at the momemnt, but can be included in future for better performancel_
- Currenlty app fetches a bunch of data like, 100 images in a single api call. It could be improved with Endless-scrolling
- Dependency injection can be solved with Dagger2
- Test cases
