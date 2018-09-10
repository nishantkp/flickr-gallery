# flickr-gallery

[![MIT licensed](https://img.shields.io/badge/license-MIT-blue.svg)](https://raw.githubusercontent.com/nishantkp/flickr-gallery/master/LICENSE)

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
<br>`format=json`</br> `nojsoncallback=1 text={USER_SEARCH_QUERY}`

_Size of images is retrieved from,_
_method,_
<br>`method=flickr.photos.getSizes`</br>

for more detail, check out this Flickr [link](https://www.flickr.com/services/api/).

### Teade-offs
_Not in app at the momemnt, but can be included in future for better performancel_
- Currenlty app fetches a bunch of data like, 100 images in a single api call. It could be improved with Endless-scrolling
- Dependency injection can be solved with Dagger2
- Test cases

### Technical-choices
- Retrofit with GsonConvertorFactory for effectively parsing data from API
- Rx for network operation, because it manages threads much better and multiple network call chains are easy.
- RecyclerView over classis listView
- DiffUtils callback to update onlu specific views, instead of whole list item. 

## UX
<img src="/ux/splash_screen.png" width="200"> <img src="/ux/dashboard_1.png" width="200"> <img src="/ux/dashboard_2.png" width="200">

## License
```
MIT License

Copyright (c) 2018 Nishant Patel
```
