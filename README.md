<h3>Gutenberg App</h3>
<li>Notes from this app:</li>
<ul>
  This app uses a public Gutenberg API to retrieve the book lists. Due to the lack of individual book's content i.e. no API for each book, the book content
is parsed from the DOM tree using Jsoup and rendered as small components. And because of this, not all books have the same DOM tree hierarchy, most of
the books have chapters listed properly while others do not, such as Moby Dick, which has all chapters listed in a single chapter.
</ul>
<ul>
  Hardware functionality such as brightness only works on physical devices, emulators do not show the change. The only way to test is to log print the local
contents.
</ul>
<ul>
  This app does not use cache or any form of storage. Data lives as long as the app is running.
</ul>
