/**
 * Here is some code that contains various promises and timeouts.
 * See if you can predict what it will print. Then run it.
 * Did you guess correctly?
 */

new Promise((r) => { console.log('p1'); r() }).then(() => console.log('p1 then'));
setTimeout(() => console.log('t1'), 0);
setTimeout(async () => {
    console.log('t2a');
    setTimeout(() => console.log('t2 t1'), 0);
    await new Promise((r) => { console.log('t2 p1'); r() }).then(() => console.log('t2 p1 then'));
    new Promise((r) => { console.log('t2 p2'); r() }).then(() => console.log('t2 p2 then'));;
    setTimeout(() => console.log('t2 t2'), 0);
    console.log('t2b');
    await new Promise((r) => { console.log('t2 p3'); r() }).then(() => console.log('t2 p3 then'));
}, 0);
setTimeout(() => console.log('t3'), 0);
new Promise(async (r) => {
    await new Promise((r) => { console.log('p2 p1'); r() });
    console.log('p2');
    r();
}).then(() => console.log('p2 then'));
new Promise((r) => {
    new Promise((r) => { console.log('p3 p1'); r() });
    console.log('p3');
    r();
}).then(() => console.log('p3 then'));
