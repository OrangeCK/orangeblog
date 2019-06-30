// Canvas Init
var c = document.getElementById('bubbles'),
  ctx = c.getContext('2d'),
  width = window.innerWidth,
  height = window.innerHeight,
  particles = 60,
  minRadius = 5,
  maxRadius = 40,
  speed = 0.0001,
  x = width / particles;

// Bubbles
var Bubbles = [];

for (var i = 0; i < particles; i++) {
  Bubbles.push({
    x: i * x,
    y: height * Math.random(),
    r: minRadius + Math.random() * (maxRadius - minRadius),
    speed: 1 * Math.random()
  });
}

function bubble() {

  c.width = width;
  c.height = height;
  for (i = 0; i < Bubbles.length; i++) {
    var b = Bubbles[i];
    console.log(i, b);
    ctx.beginPath();
    ctx.arc(b.x, b.y, b.r, 0, 2 * Math.PI);
    
    b.alpha = .5 * (b.y / height);
    b.speed += speed;
    
    ctx.strokeStyle = "rgba(255, 255, 255, .5)";
    ctx.stroke();
    ctx.fillStyle = "hsla(213, 75%, 69%," + b.alpha + ")";
    ctx.fill();
    b.y -= b.speed;
    if (b.y < 0) {
      b.y = height;
      b.speed = Math.random() * 5;
    }
  }
}

// Draw
function draw() {
  bubble();
  window.requestAnimationFrame(draw);
}

// Resize Canvas
function resizeCanvas() {
  width = window.innerWidth,
  height = window.innerHeight;
  c.width = width;
  c.height = height;
  draw();
}
resizeCanvas();
window.addEventListener('resize', resizeCanvas, false);