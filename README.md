# Yelp Clone 

## *Kaylee Doty*

**Yelp clone** displays a list of search results from the Yelp API and displays the results in a scrollable list. 

Time spent: **6** hours spent in total

## Functionality 

The following **required** functionality is completed:

* [X] Ability to query the Yelp API to get results from a search query
* [X] The search results are displayed in a RecyclerView

The following **extensions** are implemented:

* [X] User can filter search results by restaurant name

## Video Walkthrough

Here's a walkthrough of implemented user stories:

<img src='https://i.imgur.com/pjwefiQ.mp4' title='Video Walkthrough' width='' alt='Video Walkthrough' />

GIF created with [Imgur](http://imgur.com).

## Notes

When adding the edit text search bar at the top of the screen, the app would keep crashing when text was entered.
It took a while to figure out that one of the onTextChangedListener method functions was not considered implemented
because there was still a ToDo comment in the function body.

## License

    Copyright [2021] [Kaylee Doty]

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
