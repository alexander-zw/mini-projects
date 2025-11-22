/**
 * @file
 * INSTRUCTIONS - METHOD 1 (RECOMMENDED - EASY):
 * 1. Open the Forkable meals page in your browser.
 * 2. Open the Chrome console (F12 or right-click > Inspect > Console).
 * 3. Paste this entire script and press Enter.
 * 4. Run: extractMealsFromPage()
 * 5. Copy the JS object output after "Extracted meals:" right-click > Copy object.
 * 6. Paste the JSON output into an LLM chat to ask for specific kinds of meals (low calorie, low sodium, etc).
 *
 * INSTRUCTIONS - METHOD 2 (MANUAL):
 * 1. Inspect the HTML page and find `<div class="container">` with first child like `<div id="menu_favorite-1090150">`.
 * 2. Right click on it and do Copy > Copy Element.
 * 3. Copy the HTML into the `COPIED_HTML` variable below.
 * 4. Paste this script into the Chrome console and press Enter.
 * 5. Copy the JS object output after "Extracted meals:" right-click > Copy object.
 * 6. Paste the JSON output into an LLM chat to ask for specific kinds of meals (low calorie, low sodium, etc).
 */

// Extract meals from the current page
function extractMealsFromPage() {
  // Every menu item has a .card-body with an h4 and a .card-text
  const bodies = document.querySelectorAll(".card-body");

  const meals = Array.from(bodies)
    .map((body) => {
      const nameEl =
        body.querySelector("h4 span span") ||
        body.querySelector("h4 span") ||
        body.querySelector("h4");

      const descEl =
        body.querySelector(".card-text span span") ||
        body.querySelector(".card-text span") ||
        body.querySelector(".card-text");

      const name = nameEl ? nameEl.textContent.trim() : "";
      const description = descEl ? descEl.textContent.trim() : "";

      return { name, description };
    })
    .filter((m) => m.name && m.description);

  console.log("Extracted meals:", meals);
  console.log("Total meals found:", meals.length);
  return meals;
}

// Legacy function - extracts meals from an HTML string (if you already copied the HTML)
function extractMealsFromHtml(html) {
  const parser = new DOMParser();
  const doc = parser.parseFromString(html, "text/html");
  const bodies = doc.querySelectorAll(".card-body");

  const meals = Array.from(bodies)
    .map((body) => {
      const nameEl =
        body.querySelector("h4 span span") ||
        body.querySelector("h4 span") ||
        body.querySelector("h4");

      const descEl =
        body.querySelector(".card-text span span") ||
        body.querySelector(".card-text span") ||
        body.querySelector(".card-text");

      const name = nameEl ? nameEl.textContent.trim() : "";
      const description = descEl ? descEl.textContent.trim() : "";

      return { name, description };
    })
    .filter((m) => m.name && m.description);

  return meals;
}

// Example HTML data for testing the legacy extractMealsFromHtml() function.
// The new extractMealsFromPage() function doesn't need this - it reads directly from the browser page.
const EXAMPLE_HTML = `<div class="container"><!---->
  <div id="menu_favorite-1090150">
    <div class="menu-section menu-favorite">
      <div class="section-name">
        <div class="text-ellipsis section-name__text">
          <div class="v-align-middle live-input__label color-white"><img src="/mc/img/logo-icon-light.76d8f9f0.svg"
              width="25" class="mr-2"> Forkable Favorites </div>
        </div>
      </div>
      <div class="section-description"></div>
      <div class="menu-items card-group">
        <div id="menu-item-13562-5" class=""><!---->
          <div class="card-header" style="position: relative;">
            <div class=""><!----><img src="https://cdn.filestackcontent.com/resize=width:640/hKP2UwnQTGDXV8B4Tg6c"
                class="img-fluid">
              <div style="position: absolute; bottom: 0px; right: 0px;">
                <div class="sentiment-buttons has-selection no-note"><button class="like-btn selected"
                    title="I love this meal!">😍</button><button class="neutral-btn not-selected"
                    title="It's all right">😐</button><button class="dislike-btn not-selected"
                    title="I will NEVER order this meal!">👿</button><button class="add-note-btn not-selected"
                    title="No note yet">📝</button></div>
              </div>
            </div>
          </div>
          <div class="card-body"><!----><!---->
            <div class="venue-name-top bg-aqua px-3 py-2 v-align-middle"><img src="/mc/img/favorite.cd8c5246.svg"
                alt="Favorite" class="mr-2 mb-1">
              <div class="mw-0"><span class="live-input__label d-block text-ellipsis">The Cantina </span>
                <h4 class="my-0"><span><span>Blue Milk</span><!----></span></h4>
              </div>
            </div>
            <p class="card-text"><span><span>Blue milk with sugar and a hint of vanilla.</span><!----></span></p>
          </div>
          <div class="card-footer">
            <div><!---->
              <div class="clearfix mb-3 ingredients text-black">
                <div class="inner clearfix"><span>
                    <div class="align-center m-auto" style="height: 23px;"><svg width="13px" height="22px"
                        viewBox="0 0 13 22" xmlns="http://www.w3.org/2000/svg" class="svg-colored">
                        <g stroke="none" stroke-width="1" fill="none" fill-rule="evenodd">
                          <g stroke="none" stroke-width="1" fill="none" fill-rule="evenodd">
                            <path></path>
                          </g>
                        </g>
                      </svg></div><span class="icon-label">Gluten</span>
                  </span><span>
                    <div class="align-center m-auto" style="height: 23px;"><svg width="20px" height="21px"
                        viewBox="0 0 20 21" xmlns="http://www.w3.org/2000/svg" class="svg-colored">
                        <g stroke="none" stroke-width="1" fill="none" fill-rule="evenodd">
                          <g stroke="none" stroke-width="1" fill="none" fill-rule="evenodd">
                            <g class="svg__stroke--black" transform="translate(0.500000, 0.140817)" fill-rule="nonzero"
                              stroke-width="0.75">
                              <path></path>
                              <circle id="Oval-3" cx="14.8181305" cy="4.84801288" r="1.65268352"></circle>
                              <circle id="Oval-3-Copy" cx="9.53644622" cy="9.78415875" r="1.65268352"></circle>
                              <circle id="Oval-3-Copy-2" cx="4.40913758" cy="15.1314752" r="1.65268352"></circle>
                            </g>
                          </g>
                        </g>
                      </svg></div><span class="icon-label">Soy</span>
                  </span><span>
                    <div class="align-center m-auto" style="height: 23px;"><svg width="18px" height="23px"
                        viewBox="0 0 18 23" xmlns="http://www.w3.org/2000/svg" class="svg-colored">
                        <g stroke="none" stroke-width="1" fill="none" fill-rule="evenodd">
                          <g stroke="none" stroke-width="1" fill="none" fill-rule="evenodd">
                            <g class="svg__fill--black" transform="translate(1.000000, 0.000000), scale(0.9)">
                              <path></path>
                              <path></path>
                              <path></path>
                            </g>
                          </g>
                        </g>
                      </svg></div><span class="icon-label">Eggs</span>
                  </span></div>
              </div><!---->
              <div class="font-sm v-align-middle justify-content-between">
                <div class="w-100 v-align-middle justify-content-between"><strong class="color-blue font-sm"> $21.00
                  </strong></div><!---->
              </div><!---->
            </div>
          </div><!---->
        </div>
        <div id="menu-item-13562-4" class=""><!---->
          <div class="card-header" style="position: relative;">
            <div class=""><!----><img src="https://cdn.filestackcontent.com/resize=width:640/cHjtbVYNRkaNQsq8CIlF"
                class="img-fluid">
              <div style="position: absolute; bottom: 0px; right: 0px;">
                <div class="sentiment-buttons no-selection no-note"><button class="like-btn not-selected"
                    title="I love this meal!">😍</button><button class="neutral-btn not-selected"
                    title="It's all right">😐</button><button class="dislike-btn not-selected"
                    title="I will NEVER order this meal!">👿</button><button class="add-note-btn not-selected"
                    title="No note yet">📝</button></div>
              </div>
            </div>
          </div>
          <div class="card-body"><!----><!---->
            <div class="venue-name-top bg-aqua px-3 py-2 v-align-middle"><img src="/mc/img/favorite.cd8c5246.svg"
                alt="Favorite" class="mr-2 mb-1">
              <div class="mw-0"><span class="live-input__label d-block text-ellipsis">The Cantina</span>
                <h4 class="my-0"><span><span>Inflated Pork Chop</span><!----></span></h4>
              </div>
            </div>
            <p class="card-text"><span><span>Self-inflated pork chop with a crispy exterior and a juicy interior.</span><!----></span></p>
          </div>
          <div class="card-footer">
            <div><!---->
              <div class="clearfix mb-3 ingredients text-black">
                <div class="inner clearfix"><span>
                    <div class="align-center m-auto" style="height: 23px;"><svg width="13px" height="22px"
                        viewBox="0 0 13 22" xmlns="http://www.w3.org/2000/svg" class="svg-colored">
                        <g stroke="none" stroke-width="1" fill="none" fill-rule="evenodd">
                          <g stroke="none" stroke-width="1" fill="none" fill-rule="evenodd">
                            <path></path>
                          </g>
                        </g>
                      </svg></div><span class="icon-label">Gluten</span>
                  </span><span>
                    <div class="align-center m-auto" style="height: 23px;"><svg width="20px" height="21px"
                        viewBox="0 0 20 21" xmlns="http://www.w3.org/2000/svg" class="svg-colored">
                        <g stroke="none" stroke-width="1" fill="none" fill-rule="evenodd">
                          <g stroke="none" stroke-width="1" fill="none" fill-rule="evenodd">
                            <g class="svg__stroke--black" transform="translate(0.500000, 0.140817)" fill-rule="nonzero"
                              stroke-width="0.75">
                              <path></path>
                              <circle id="Oval-3" cx="14.8181305" cy="4.84801288" r="1.65268352"></circle>
                              <circle id="Oval-3-Copy" cx="9.53644622" cy="9.78415875" r="1.65268352"></circle>
                              <circle id="Oval-3-Copy-2" cx="4.40913758" cy="15.1314752" r="1.65268352"></circle>
                            </g>
                          </g>
                        </g>
                      </svg></div><span class="icon-label">Soy</span>
                  </span></div>
              </div><!---->
              <div class="font-sm v-align-middle justify-content-between">
                <div class="w-100 v-align-middle justify-content-between"><strong class="color-blue font-sm"> $16.00
                  </strong></div><!---->
              </div><!---->
            </div>
          </div><!---->
        </div>
      </div>
    </div>
  </div>
  <div id="menu13562-1090150">
    <div class="menu-section">
      <div class="section-name">
        <div class="section-name__text break-all">FOR YOU</div>
      </div>
      <div class="section-description"> </div>
      <div class="menu-items card-group">
        <div id="menu-item-13562-2" class=""><!---->
          <div class="card-header" style="position: relative;">
            <div class=""><!----><img src="https://cdn.filestackcontent.com/resize=width:640/pyn1jMJmQnq8Lt54uLAC"
                class="img-fluid">
              <div style="position: absolute; bottom: 0px; right: 0px;">
                <div class="sentiment-buttons no-selection no-note"><button class="like-btn not-selected"
                    title="I love this meal!">😍</button><button class="neutral-btn not-selected"
                    title="It's all right">😐</button><button class="dislike-btn not-selected"
                    title="I will NEVER order this meal!">👿</button><button class="add-note-btn not-selected"
                    title="No note yet">📝</button></div>
              </div>
            </div>
          </div>
          <div class="card-body"><!----><!---->
            <h4><span><span>Displacer Beast Steak</span><!----></span></h4>
            <p class="card-text"><span><span>Served with a side of crispy potatoes and onions.</span><!----></span></p>
          </div>
          <div class="card-footer">
            <div><!---->
              <div class="clearfix mb-3 ingredients text-black">
                <div class="inner clearfix"><span>
                    <div class="align-center m-auto" style="height: 23px;"><svg width="14px" height="19px"
                        viewBox="0 0 14 19" xmlns="http://www.w3.org/2000/svg" class="svg-colored">
                        <g stroke="none" stroke-width="1" fill="none" fill-rule="evenodd">
                          <g stroke="none" stroke-width="1" fill="none" fill-rule="evenodd">
                            <path></path>
                          </g>
                        </g>
                      </svg></div><span class="icon-label">Dairy</span>
                  </span><span>
                    <div class="align-center m-auto" style="height: 23px;"><svg width="13px" height="22px"
                        viewBox="0 0 13 22" xmlns="http://www.w3.org/2000/svg" class="svg-colored">
                        <g stroke="none" stroke-width="1" fill="none" fill-rule="evenodd">
                          <g stroke="none" stroke-width="1" fill="none" fill-rule="evenodd">
                            <path></path>
                          </g>
                        </g>
                      </svg></div><span class="icon-label">Gluten</span>
                  </span><span>
                    <div class="align-center m-auto" style="height: 23px;"><svg width="20px" height="21px"
                        viewBox="0 0 20 21" xmlns="http://www.w3.org/2000/svg" class="svg-colored">
                        <g stroke="none" stroke-width="1" fill="none" fill-rule="evenodd">
                          <g stroke="none" stroke-width="1" fill="none" fill-rule="evenodd">
                            <g class="svg__stroke--black" transform="translate(0.500000, 0.140817)" fill-rule="nonzero"
                              stroke-width="0.75">
                              <path></path>
                              <circle id="Oval-3" cx="14.8181305" cy="4.84801288" r="1.65268352"></circle>
                              <circle id="Oval-3-Copy" cx="9.53644622" cy="9.78415875" r="1.65268352"></circle>
                              <circle id="Oval-3-Copy-2" cx="4.40913758" cy="15.1314752" r="1.65268352"></circle>
                            </g>
                          </g>
                        </g>
                      </svg></div><span class="icon-label">Soy</span>
                  </span></div>
              </div><!---->
              <div class="font-sm v-align-middle justify-content-between">
                <div class="w-100 v-align-middle justify-content-between"><strong class="color-blue font-sm"> $21.00
                  </strong></div><!---->
              </div><!---->
            </div>
          </div><!---->
        </div>
        <div id="menu-item-13562-3" class=""><!---->
          <div class="card-header" style="position: relative;">
            <div class=""><!----><img src="https://cdn.filestackcontent.com/resize=width:640/uT4Vh6ZxT7CdrUorkxos"
                class="img-fluid">
              <div style="position: absolute; bottom: 0px; right: 0px;">
                <div class="sentiment-buttons no-selection no-note"><button class="like-btn not-selected"
                    title="I love this meal!">😍</button><button class="neutral-btn not-selected"
                    title="It's all right">😐</button><button class="dislike-btn not-selected"
                    title="I will NEVER order this meal!">👿</button><button class="add-note-btn not-selected"
                    title="No note yet">📝</button></div>
              </div>
            </div>
          </div>
          <div class="card-body"><!----><!---->
            <h4><span><span>The Giant's Foot</span><!----></span></h4>
            <p class="card-text"><span><span>A foot-long slab of meat served with a side of crispy potatoes and onions.</span><!----></span></p>
          </div>
          <div class="card-footer">
            <div><!----><!----><!---->
              <div class="font-sm v-align-middle justify-content-between">
                <div class="w-100 v-align-middle justify-content-between"><strong class="color-blue font-sm"> $18.00
                  </strong></div><!---->
              </div><!---->
            </div>
          </div><!---->
        </div>
      </div>
    </div>
  </div>
</div>`;

// OPTIONAL: Replace this with your copied HTML if using METHOD 2 option (b).
// This example HTML is just for reference. METHOD 1 (extractMealsFromPage) doesn't need this.
const COPIED_HTML = ``;

const meals = COPIED_HTML
  ? extractMealsFromHtml(COPIED_HTML)
  : extractMealsFromPage();

// You can uncomment these to log the meals to the console, but in the browser console getting the meals variable will be easier.
// console.log(meals);
// console.log(JSON.stringify(meals, null, 2));
