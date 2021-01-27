Skip to content Why GitHub? Team Enterprise Explore Marketplace Pricing Search
Sign in Sign up Natron-code / paint 101 Code Issues Pull requests 1 Actions
Projects Security Insights Join GitHub today GitHub is home to over 50 million
developers working together to host and review code, manage projects, and build
software together. paint/navBar.vue @BerryYoghurt BerryYoghurt Fix text/xml
issue Latest commit 6b9284a 7 hours ago History 2 contributors
@Natron-code@BerryYoghurt 979 lines (931 sloc) 38.5 KB

<template>
  <div id="app">
    <div class="box">
      <div class="box-compon" id="save" @click="tool('save')">
        <img
          src="https://img.icons8.com/ultraviolet/40/000000/save-as.png"
          style="height:58%;position: relative;margin:13%;"
        />
      </div>

      <label class="box-compon" id="load" style="height:68px">
        <img
          src="https://img.icons8.com/nolan/64/load-cargo.png"
          style="height:58%;position: relative;margin:13%;"
        />
        <input
          id="filePick"
          type="file"
          accept=".json, .xml"
          @click="tool('load')"
        />
      </label>

      <div class="box-compon" id="undo" @click="tool('undo')">
        <img
          src="https://img.icons8.com/nolan/64/undo.png"
          style="height:58%;position: relative;margin:13%;"
        />
      </div>
      <div class="box-compon" id="redo" @click="tool('redo')">
        <img
          src="https://img.icons8.com/nolan/64/redo.png"
          style="height:58%;position: relative;margin:13%;"
        />
      </div>
      <div class="box-compon" id="line" @click="tool('line')">
        <img
          src="https://img.icons8.com/dusk/64/000000/line.png"
          style="height:58%;position: relative;margin:13%;"
        />
      </div>
      <div class="box-compon" id="triangle" @click="tool('triangle')">
        <img
          src="https://img.icons8.com/fluent/48/000000/triangle-stroked.png"
          style="height:58%;position: relative;margin:13%;"
        />
      </div>
      <div class="box-compon" id="rectangle" @click="tool('rectangle')">
        <img
          src="https://img.icons8.com/nolan/64/rectangle.png"
          style="height:68%;position: relative;margin:13%;"
        />
      </div>
      <div class="box-compon" id="square" @click="tool('square')">
        <img
          src="https://img.icons8.com/nolan/64/rounded-square.png"
          style="height:68%;position: relative;margin:13%;"
        />
      </div>
      <div class="box-compon" id="circle" @click="tool('circle')">
        <img
          src="https://img.icons8.com/nolan/64/filled-circle.png"
          style="height:68%;position: relative;margin:13%;"
        />
      </div>
      <div class="box-compon" id="ellipse" @click="tool('ellipse')">
        <img
          src="https://img.icons8.com/color/48/000000/ellipse.png"
          style="height:68%;position: relative;margin:13%;"
        />
      </div>
      <div class="box-compon" id="copy" @click="tool('copy')">
        <img
          src="https://img.icons8.com/dusk/64/000000/copy.png"
          style="height:68%;position: relative;margin:13%;"
        />
      </div>
      <div class="box-compon" id="resize" @click="tool('resize')">
        <img
          src="https://img.icons8.com/nolan/64/resize.png"
          style="height:68%;position: relative;margin:13%;"
        />
      </div>
      <div class="box-compon" id="move" @click="tool('move')">
        <img
          src="https://img.icons8.com/nolan/64/move.png"
          style="height:68%;position: relative;margin:13%;"
        />
      </div>
      <div class="box-compon" id="delete" @click="tool('delete')">
        <img
          src="https://img.icons8.com/dusk/64/000000/delete-forever.png"
          style="height:68%;position: relative;margin:13%;"
        />
      </div>
      <div class="box-compon" id="fill" @click="tool('fill')">
        <img
          src="https://img.icons8.com/dusk/64/000000/fill-color.png"
          style="height:68%;position: relative;margin:13%;"
        />
      </div>
      <input id="colorPick" type="color" />
      <canvas id="my-canvas" width="1320" height="612"></canvas>
    </div>
  </div>
</template>
<script>
export default {
  name: "app",
  data: function() {
    return {
      current: "tool",
      canvas: "",
      URL: "http://localhost:8091",
      //current shape location for move/resize/copy
      shapeInActionLocation: -1,
      //refrence to a resized shape
      fixed: "",
      holdedColor: "#ffffff",
      br: "",
      //mouse location
      mouseup: { x: 0, y: 0 },
      mousedown: { x: 0, y: 0 },
      //current location of the mouse
      loc: { x: 0, y: 0 },
      ///////////////////////////////
      ctx: "",
      savedImageData: "",
      dragging: false,
      //Arrays to held App's shapes
      shapes: [],
      shapedRedo: [],
      uploadedFile: null,
      fillColor: null,
      borderColor: "#000000",
      linwidth: 2,
      canvasWidth: 1320,
      canvasHeight: 612,
      bounds: {
        x: 0,
        y: 0,
        width: 0,
        height: 0,
      },
    };
  },
  methods: {
    tool: function(selectedTool) {
      //highlighted the selected button
      document.getElementById("save").className = "box-compon";
      document.getElementById("load").className = "box-compon";
      document.getElementById("undo").className = "box-compon";
      document.getElementById("redo").className = "box-compon";
      document.getElementById("line").className = "box-compon";
      document.getElementById("triangle").className = "box-compon";
      document.getElementById("rectangle").className = "box-compon";
      document.getElementById("square").className = "box-compon";
      document.getElementById("circle").className = "box-compon";
      document.getElementById("ellipse").className = "box-compon";
      document.getElementById("copy").className = "box-compon";
      document.getElementById("resize").className = "box-compon";
      document.getElementById("move").className = "box-compon";
      document.getElementById("delete").className = "box-compon";
      document.getElementById("fill").className = "box-compon";
      document.getElementById(selectedTool).className = "box-compon-selected";
      this.current = selectedTool;
      if (selectedTool === "undo") {
        //backend stuff
        this.undo();
      } else if (selectedTool === "redo") {
        //backend stuff
        this.redo();
      } else if (selectedTool === "save") {
        //some stuff
        this.save();
      } else if (selectedTool === "load") {
        //some stuff
        ///this.load();
      }
    },

    save: function() {
      //Assuming my version of backend
      const link = this.URL + "/save";
      let needjson = confirm(
        "Save as JSON? OK == yes, json, Cancel == no, xml"
      );
      fetch(link, {
        method: "GET",
        headers: {
          Accept: needjson ? "application/json" : "application/xml",
          mode: "no-cors",
        },
      })
        .then((r) => r.blob())
        .then(function(data) {
          let filename = needjson ? "canvas.json" : "canvas.xml";
          if (window.navigator.msSaveOrOpenBlob) {
            window.navigator.msSaveBlob(data, filename);
          } else {
            var elem = window.document.createElement("a");
            elem.href = window.URL.createObjectURL(data);
            elem.download = filename;
            document.body.appendChild(elem);
            elem.click();
            document.body.removeChild(elem);
          }
        });
    },
    load: function(event) {
      //Assuming the request format in my version of the backend:
      const link = this.URL + "/upload";
      const file = event.target.files[0];
      let type;
      if (file.name.slice(file.name.length - 4, file.name.length) == ".xml") {
        type = "application/xml";
      } else {
        type = "application/json";
      }
      fetch(link, {
        method: "POST",
        headers: {
          "Content-Type": type,
          mode: "no-cors",
        },
        body: file,
      }).then((data) => {
        this.reset(data);
        let url = this.URL + "/get";
        fetch(url)
          .then((response) => {
            return response.json();
          })
          .then((data) => {
            this.br = this.convertArr(data);
            //perform the operation in the frontend
            this.shapes = this.br;
            console.log(data.length);
            this.displayShapes();
            this.SaveCanvasImage();
            this.RedrawCanvasImage();
          });
      });
    },
    reset: function(response) {
      //TODO reset app and parse response
      console.log(response);
      this.shapes = [];
      this.displayShapes();
      this.SaveCanvasImage();
      this.RedrawCanvasImage();
    },
    undo: function() {
      /////////////////////////////////////////////////////////////
      //backend stuff
      if (this.shapes.length > 0) {
        //report the undo process to the backend
        let url = this.URL + "/undo";
        fetch(url)
          .then((response) => {
            return response.json();
          })
          .then((data) => {
            this.br = this.convertArr(data);
            //console.log(this.br[0]._color);
            this.shapes = this.br;
            //console.log(this.shapes.length+' '+this.shapes);
            this.displayShapes();
            this.SaveCanvasImage();
            this.RedrawCanvasImage();
          });
        //perform the operation in the frontend
      }
    },
    redo: function() {
      /////////////////////////////////////////////////////////////
      //backend stuff
      //report the redo process to the backend
      let url = this.URL + "/redo";
      fetch(url)
        .then((response) => {
          return response.json();
        })
        .then((data) => {
          this.br = this.convertArr(data);
          //perform the operation in the frontend
          this.shapes = this.br;
          this.displayShapes();
          this.SaveCanvasImage();
          this.RedrawCanvasImage();
        });
    },
    //convert array of shapes from backend fromat to frontend format
    convertArr: function(arr) {
      let _hold = [];
      arr.forEach((elem) => {
        //prepare the elements of the object
        let _id = elem.id;
        let _type = elem.type;
        let _fillColor = "#" + elem.fillColor;
        let _mousedown = { x: elem.f_x1, y: elem.f_y1 };
        let _mouseup = { x: elem.f_x2, y: elem.f_y2 };
        let _dim = {
          x: elem.boundingBox_left,
          y: elem.boundingBox_top,
          width: elem.width,
          height: elem.height,
        };
        //check odd cases
        if (elem.type === "circle" || elem.type === "square") {
          _dim.x = Math.min(_mousedown.x, _mouseup.x);
          _dim.y = Math.min(_mousedown.y, _mouseup.y);
          _dim.width = elem.width / 2;
          _dim.height = elem.width / 2;
        }
        //adjust the ellipse shape
        else if (elem.type === "ellipse") {
          _dim.x = Math.min(_mousedown.x, _mouseup.x);
          _dim.y = Math.min(_mousedown.y, _mouseup.y);
          _dim.width = elem.width / 2;
          _dim.height = elem.height / 2;
        }
        _hold.push({
          dim: _dim,
          type: _type,
          id: _id,
          _color: _fillColor,
          mouse_up: _mouseup,
          mouse_down: _mousedown,
        });
      });
      return _hold;
    },
    //get position relative to cavan's panel
    getPosition: function(x, y) {
      let cav = this.canvas.getBoundingClientRect();
      return { x: x - cav.left, y: y - cav.top };
    },
    //////////////////////////////////////////////////////////
    updatebounds: function(pointt) {
      this.bounds.width = Math.abs(pointt.x - this.mousedown.x);
      this.bounds.height = Math.abs(pointt.y - this.mousedown.y);
      this.bounds.x = pointt.x > this.mousedown.x ? this.mousedown.x : pointt.x;
      this.bounds.y = pointt.y > this.mousedown.y ? this.mousedown.y : pointt.y;
    },
    setMouseDown: function(event) {
      if (!this.dragging) {
        this.canvas.style.cursor = "crosshair";
        this.loc = this.getPosition(event.clientX, event.clientY);
        //save
        this.SaveCanvasImage();
        this.mousedown.x = this.loc.x;
        this.mousedown.y = this.loc.y;
        this.dragging = true;
        if (this.shapes.length > 0) {
          //adjust draging
          this.dragging = !(
            this.current === "fill" ||
            this.current === "delete" ||
            this.current === "copy" ||
            this.current === "resize" ||
            this.current === "move"
          );
          if (this.current === "fill") {
            //handle fill
            this.getShapeBack(this.mousedown, "fill");
          } else if (this.current === "move") {
            ///////////////////////////
            //backend stuff
            this.getShapeBack(this.mousedown, "move");
          } else if (this.current === "delete") {
            //handle delete
            this.getShapeBack(this.mousedown, "delete");
          } else if (this.current === "copy") {
            //handle copy
            this.getShapeBack(this.mousedown, "copy");
          } else if (this.current === "resize") {
            //backend stuff
            this.getShapeBack(this.mousedown, "resize");
          }
        }
      }
      //check if user gone out of canvas then back
      else {
        //this.setMouseUp("mouseup");
      }
    },
    //get the ID of the shape corresponding to mouse click an assign it to shapeInAction
    //and make some stuff based on the method passed to it
    //backend stuff
    getShapeBack: function(_location, mthd) {
      /*let url=this.URL+'/getId/'+
      [_location.x].toString+'/'+[_location.y].toString;*/
      let url = this.URL + "/getId/" + _location.x + "/" + _location.y;
      this.shapeInActionLocation = -1;
      console.log(url);
      fetch(url)
        .then((response) => {
          return response.json();
        })
        .then((data) => {
          for (let i = 0; i < this.shapes.length; i++) {
            if (data === this.shapes[i].id) {
              this.shapeInActionLocation = i;
              break;
            }
          }
          //////////////////////////////////////////////////////////////////
          if (mthd === "fill" && this.shapeInActionLocation > -1) {
            //set color in frontend
            this.setColor(this.shapeInActionLocation, this.fillColor.value);
            //refresh the image
            this.displayShapes();
            //send changes to the backend ctx.fillColor
            let url_ =
              this.URL +
              "/colorfill/" +
              this.shapes[this.shapeInActionLocation].id +
              "/" +
              this.fillColor.value.replace("#", "");
            fetch(url_).then((response) => {
              return response.json();
            });
          }
          //////////////////////////////////////////////////////////////////
          else if (mthd === "delete" && this.shapeInActionLocation > -1) {
            //delete shape from front end
            let url_ =
              this.URL +
              "/delete/" +
              this.shapes[this.shapeInActionLocation].id;
            this.deleteShape(this.shapeInActionLocation);
            //update the image
            this.displayShapes();
            //send changes to backend
            fetch(url_).then((response) => {
              return response.json();
            });
          }
          //////////////////////////////////////////////////////////////////
          else if (mthd === "copy" && this.shapeInActionLocation > -1) {
            //create object to hold new shape
            let holder = Object.assign(
              {},
              this.shapes[this.shapeInActionLocation]
            );
            //update the ID to another one
            holder.id = this.getID();
            //update the location of the shape to be 20px from the upper-left corner of the canvas
            let dummyDim = {
              x: 20,
              y: 20,
              width: holder.dim.width,
              height: holder.dim.height,
            };
            if (holder.type === "circle") {
              dummyDim.x =
                holder.dim.x - (holder.mouse_down.x - (holder.dim.width + 20));
              dummyDim.y =
                holder.dim.y - (holder.mouse_down.y - (holder.dim.width + 20));
            } else if (holder.type === "ellipse") {
              dummyDim.x =
                holder.dim.x - (holder.mouse_down.x - (holder.dim.width + 20));
              dummyDim.y =
                holder.dim.y - (holder.mouse_down.y - (holder.dim.height + 20));
            } else if (holder.type === "square") {
              dummyDim.x =
                holder.dim.x - (holder.mouse_down.x - (holder.dim.width + 20));
              dummyDim.y =
                holder.dim.y - (holder.mouse_down.y - (holder.dim.width + 20));
            }
            //update the location of the mouse clicks
            let dumMouseDown = {
              x: holder.mouse_down.x - (holder.dim.x - 20),
              y: holder.mouse_down.y - (holder.dim.y - 20),
            };
            let dumMouseUp = {
              x: holder.mouse_up.x - (holder.dim.x - 20),
              y: holder.mouse_up.y - (holder.dim.y - 20),
            };
            if (holder.type === "circle") {
              dumMouseDown.x = holder.dim.width + 20;
              dumMouseDown.y = holder.dim.width + 20;
              dumMouseUp.x =
                holder.mouse_up.x -
                (holder.mouse_down.x - (holder.dim.width + 20));
              dumMouseUp.y =
                holder.mouse_up.y -
                (holder.mouse_down.y - (holder.dim.width + 20));
            } else if (holder.type === "ellipse") {
              dumMouseDown.x = holder.dim.width + 20;
              dumMouseDown.y = holder.dim.height + 20;
              dumMouseUp.x =
                holder.mouse_up.x -
                (holder.mouse_down.x - (holder.dim.width + 20));
              dumMouseUp.y =
                holder.mouse_up.y -
                (holder.mouse_down.y - (holder.dim.height + 20));
            } else if (holder.type === "square") {
              dumMouseDown.x = holder.dim.width + 20;
              dumMouseDown.y = holder.dim.width + 20;
              dumMouseUp.x =
                holder.mouse_up.x -
                (holder.mouse_down.x - (holder.dim.width + 20));
              dumMouseUp.y =
                holder.mouse_up.y -
                (holder.mouse_down.y - (holder.dim.width + 20));
            }
            holder.dim = dummyDim;
            holder.mouse_down = dumMouseDown;
            holder.mouse_up = dumMouseUp;
            //add the copied shape to the shapes then display
            this.shapes.push(holder);
            this.displayShapes();
            //send changes to the backend
            this.__sendBack(holder, "copy");
          } else if (mthd === "move" && this.shapeInActionLocation > -1) {
            //hold the old shape
            let _holder = Object.assign(
              {},
              this.shapes[this.shapeInActionLocation]
            );
            //prevent the holded shape from being drawn
            this.shapes[this.shapeInActionLocation].type = "dummy";
            this.holdedColor = _holder._color;
            this.displayShapes();
            this.SaveCanvasImage();
            this.RedrawCanvasImage();
            //return the refrence to the selected object
            this.shapes[this.shapeInActionLocation] = _holder;
            console.log(this.holdedColor);
          } else if (mthd === "resize" && this.shapeInActionLocation > -1) {
            //hold the old shape
            this.fixed = Object.assign(
              {},
              this.shapes[this.shapeInActionLocation]
            );
            this.current = this.shapes[this.shapeInActionLocation].type;
            //prevent current shape from being drawn
            this.shapes[this.shapeInActionLocation].type = "dummy";
            this.holdedColor = this.fixed._color;
            this.displayShapes();
            this.SaveCanvasImage();
            this.RedrawCanvasImage();
            //return the refrence to the selected object
            this.mousedown.x = this.fixed.mouse_down.x;
            this.mousedown.y = this.fixed.mouse_down.y;
            this.dragging = true;
          }
          if (mthd === "resize" && this.shapeInActionLocation < 0) {
            this.fixed = "a";
          }
          //set location in action to -1 after performing the actions on some methods
          this.shapeInActionLocation =
            mthd != "move" && mthd != "resize"
              ? -1
              : this.shapeInActionLocation;
        });
    },
    //set color for a shape
    setColor: function(loc_, color_) {
      //check if there was a shape from backend
      if (loc_ > -1) {
        this.shapes[loc_]._color = color_;
      }
    },
    //delete a returned shape if found
    deleteShape: function(_loc) {
      if (_loc > -1) {
        this.shapes.splice(_loc, 1);
      }
    },
    resizeShape: function(shape_) {
      if (shape_ != null) {
        let ID = shape_.id;
        for (let i = 0; i < this.shapes.length; i++) {
          if (ID === this.shapes[i].id) {
            this.shapes[i] = shape_;
            break;
          }
        }
      }
    },
    setMouseMove: function(event) {
      this.canvas.style.cursor = "crosshair";
      this.loc = this.getPosition(event.clientX, event.clientY);
      if (this.dragging && !"move".includes(this.current)) {
        //////
        if (
          (this.fixed != "" && this.shapeInActionLocation > -1) ||
          this.fixed === ""
        ) {
          this.RedrawCanvasImage();
          this.updateboundsActive(this.loc);
        }
      }
      if (this.current === "move" && this.shapeInActionLocation > -1) {
        //if(this.shapes[this.shapeInActionLocation].type)
        this.shapes[this.shapeInActionLocation].mouse_up.x =
          this.shapes[this.shapeInActionLocation].mouse_up.x -
          (this.shapes[this.shapeInActionLocation].dim.x - this.loc.x);
        this.shapes[this.shapeInActionLocation].mouse_up.y =
          this.shapes[this.shapeInActionLocation].mouse_up.y -
          (this.shapes[this.shapeInActionLocation].dim.y - this.loc.y);
        this.shapes[this.shapeInActionLocation].mouse_down.x =
          this.shapes[this.shapeInActionLocation].mouse_down.x -
          (this.shapes[this.shapeInActionLocation].dim.x - this.loc.x);
        this.shapes[this.shapeInActionLocation].mouse_down.y =
          this.shapes[this.shapeInActionLocation].mouse_down.y -
          (this.shapes[this.shapeInActionLocation].dim.y - this.loc.y);
        this.shapes[this.shapeInActionLocation].dim.x = this.loc.x;
        this.shapes[this.shapeInActionLocation].dim.y = this.loc.y;
        this.RedrawCanvasImage();
        this.drawShapeAction(this.shapes[this.shapeInActionLocation]);
      }
    },
    displayShapes: function() {
      //get an array on shapes and display them
      this.ctx.clearRect(0, 0, this.canvasWidth, this.canvasHeight);
      ////////////////////////////////////////////////////////////////
      for (let i = 0; i < this.shapes.length; i++) {
        this.colorShape(this.shapes[i]);
        let typ = this.shapes[i].type;
        let dims = this.shapes[i].dim;
        let _mouseup = this.shapes[i].mouse_up;
        let _mousedown = this.shapes[i].mouse_down;
        if (typ === "line") {
          this.ctx.beginPath();
          this.ctx.moveTo(_mousedown.x, _mousedown.y);
          this.ctx.lineTo(_mouseup.x, _mouseup.y);
          this.ctx.stroke();
        } else if (typ === "triangle") {
          let polypoints = [
            { x: dims.x + dims.width / 2, y: dims.y },
            { x: dims.x, y: dims.y + dims.height },
            { x: dims.x + dims.width, y: dims.y + dims.height },
          ];
          this.ctx.beginPath();
          this.ctx.moveTo(polypoints[0].x, polypoints[0].y);
          for (let i = 1; i < 3; i++) {
            this.ctx.lineTo(polypoints[i].x, polypoints[i].y);
          }
          this.ctx.closePath();
          this.ctx.stroke();
        } else if (typ === "rectangle") {
          this.ctx.strokeRect(dims.x, dims.y, dims.width, dims.height);
        } else if (typ === "square") {
          this.ctx.strokeRect(
            _mousedown.x - dims.width,
            _mousedown.y - dims.width,
            dims.width * 2,
            dims.width * 2
          );
        } else if (typ === "circle") {
          let radius = dims.width;
          this.ctx.beginPath();
          this.ctx.arc(_mousedown.x, _mousedown.y, radius, 0, Math.PI * 2);
          this.ctx.stroke();
        } else if (typ === "ellipse") {
          let radiusX = dims.width;
          let radiusY = dims.height;
          this.ctx.beginPath();
          this.ctx.ellipse(
            _mousedown.x,
            _mousedown.y,
            radiusX,
            radiusY,
            0,
            0,
            Math.PI * 2
          );
          this.ctx.stroke();
        }
        //update
        this.SaveCanvasImage();
        this.RedrawCanvasImage();
      }
      this.SaveCanvasImage();
      this.RedrawCanvasImage();
    },
    setMouseUp: function(event) {
      this.canvas.style.cursor = "default";
      this.loc = this.getPosition(event.clientX, event.clientY);
      this.SaveCanvasImage();
      this.RedrawCanvasImage();
      this.updatebounds(this.loc);
      this.mouseup = this.loc;
      //////////////////////////////////////////////////////// console
      //insert shapes and sent them to backend if there was a shape
      if (
        "circlelinetriangleellipserectanglesquare".includes(this.current) &&
        !isNaN(this.bounds.x) &&
        this.dragging
      ) {
        if (this.fixed === "") {
          this.insertShape();
          this.__sendBack(this.shapes[this.shapes.length - 1], "copy");
        } else if (this.fixed != "") {
          console.log([this.current, this.shapeInActionLocation]);
          if (this.shapeInActionLocation > -1) {
            this.shapes[this.shapeInActionLocation] = this.getShape();
            this.shapes[this.shapeInActionLocation].id = this.fixed.id;
            this.shapes[this.shapeInActionLocation]._color = this.fixed._color;
            this.displayShapes();
            this.RedrawCanvasImage();
            this.SaveCanvasImage();
            this.__sendBack(this.shapes[this.shapeInActionLocation], "resize");
          }
          this.current = "resize";
          this.holdedColor = "#ffffff";
        }
      } else if (this.current === "move" && this.shapeInActionLocation > -1) {
        //@PutMapping("/move/{id}/{x}/{y}/{width}/{height}/{f_x1}/{f_y1}/{f_x2}/{f_y2}")
        //("/resize or,move/{id}/{x}/{y}/{width}/{height}/{f_x1}/{f_y1}/{f_x2}/{f_y2}")
        //send new information to the backend
        //need to update
        this.displayShapes();
        this.RedrawCanvasImage();
        this.SaveCanvasImage();
        this.__sendBack(this.shapes[this.shapeInActionLocation], this.current);
      }
      this.shapeInActionLocation = -1;
      this.fixed = "";
      this.dragging = false;
      this.holdedColor = "#ffffff";
    },
    //get a shape object from current data
    //shape on format of {dim:x,y upperleft,width,height of the bounding box,type,id,color,mouseup,mousedown}
    getShape: function() {
      let dimCloned = Object.assign({}, this.bounds);
      let _mouseup = Object.assign({}, this.mouseup);
      let _mousedown = Object.assign({}, this.mousedown);
      let _id = this.getID();
      let _type = this.current;
      //start with white as a default color//id:_id,type:_type,
      return {
        dim: dimCloned,
        type: _type,
        id: _id,
        _color: "#ffffff",
        mouse_up: _mouseup,
        mouse_down: _mousedown,
      };
    },
    insertShape: function() {
      if (
        this.mouseup.x != this.mousedown.x ||
        this.mouseup.y != this.mousedown.y
      ) {
        //id-color
        this.shapes.push(this.getShape());
      }
    },
    //send shape to backend with corresponding attributes
    __sendBack: function(_shape, mthd) {
      ////////////backend
      let url;
      if (mthd === "copy") {
        url = this.URL + "/add";
      }
      //adjust the shape object to the controller in backend
      //boundingBox_top assumed as y's in top left corner
      //left assumed as x's in top left corner
      //fx1,fy1,fx2,fy2 for the line
      let fx1 = _shape.mouse_down.x;
      let fy1 = _shape.mouse_down.y;
      let fx2 = _shape.mouse_up.x;
      let fy2 = _shape.mouse_up.y;
      let boundTop = _shape.dim.y;
      let boundLeft = _shape.dim.x;
      let boundWidth = _shape.dim.width;
      let boundHeight = _shape.dim.height;
      //adjust the circle shape
      if (_shape.type === "circle" || _shape.type === "square") {
        boundTop = _shape.mouse_down.y - _shape.dim.width;
        boundLeft = _shape.mouse_down.x - _shape.dim.width;
        boundWidth = _shape.dim.width * 2;
        boundHeight = _shape.dim.width * 2;
      }
      //adjust the ellipse shape
      else if (_shape.type === "ellipse") {
        boundTop = _shape.mouse_down.y - _shape.dim.height;
        boundLeft = _shape.mouse_down.x - _shape.dim.width;
        boundWidth = _shape.dim.width * 2;
        boundHeight = _shape.dim.height * 2;
      }
      //prepare the object
      let dat = {
        id: _shape.id,
        type: _shape.type,
        lineColor: this.borderColor,
        fillColor: _shape._color.replace("#", ""),
        boundingBox_top: boundTop,
        boundingBox_left: boundLeft,
        width: boundWidth,
        height: boundHeight,
        f_x1: fx1,
        f_y1: fy1,
        f_x2: fx2,
        f_y2: fy2,
      };
      if (mthd != "copy") {
        if (mthd === "move") {
          mthd = "resize";
        }
        url =
          this.URL +
          "/" +
          mthd +
          "/" +
          dat.id +
          "/" +
          dat.boundingBox_left +
          "/" +
          dat.boundingBox_top +
          "/" +
          dat.width +
          "/" +
          dat.height +
          "/" +
          dat.f_x1 +
          "/" +
          dat.f_y1 +
          "/" +
          dat.f_x2 +
          "/" +
          dat.f_y2;
      }
      //send the shape to backend
      if (mthd === "copy") {
        fetch(url, {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify(dat),
        }).then((response) => response.json());
      } else {
        fetch(url).then((response) => {
          return response.json();
        });
      }
    },
    //assign an ID to the current shape
    getID: function() {
      //start with random number
      let ID = Math.floor(Math.random() * 1000000);
      //check all ID's
      this.shapes.forEach((elem) => {
        while (elem.id === ID) {
          ID = Math.floor(Math.random() * 1000000);
        }
      });
      return ID;
    },
    updateboundsActive: function(locati) {
      //live update while moving
      this.updatebounds(locati);
      this.drawShape();
    },
    //for currnt Shape
    drawShape: function() {
      this.ctx.strokeStyle = this.borderColor;
      this.ctx.lineWidth = this.linwidth;
      let flg =
        this.holdedColor != "#ffffff" &&
        (this.current === "move" ||
          (this.fixed != "" && this.shapeInActionLocation > -1));
      if (flg) {
        this.ctx.fillStyle = this.holdedColor;
      }
      if (this.current === "rectangle") {
        if (flg) {
          this.ctx.fillRect(
            this.bounds.x,
            this.bounds.y,
            this.bounds.width,
            this.bounds.height
          );
        }
        this.ctx.strokeRect(
          this.bounds.x,
          this.bounds.y,
          this.bounds.width,
          this.bounds.height
        );
        //color in 129////////////
      } else if (this.current === "square") {
        if (flg) {
          this.ctx.fillRect(
            this.mousedown.x - this.bounds.width,
            this.mousedown.y - this.bounds.width,
            this.bounds.width * 2,
            this.bounds.width * 2
          );
        }
        this.ctx.strokeRect(
          this.mousedown.x - this.bounds.width,
          this.mousedown.y - this.bounds.width,
          this.bounds.width * 2,
          this.bounds.width * 2
        );
      } else if (this.current === "line") {
        //Draw Line
        this.ctx.beginPath();
        this.ctx.moveTo(this.mousedown.x, this.mousedown.y);
        this.ctx.lineTo(this.loc.x, this.loc.y);
        this.ctx.stroke();
      } else if (this.current === "circle") {
        let radius = this.bounds.width;
        this.ctx.beginPath();
        this.ctx.arc(
          this.mousedown.x,
          this.mousedown.y,
          radius,
          0,
          Math.PI * 2
        );
        if (flg) {
          this.ctx.fill();
        }
        this.ctx.stroke(); //////////////
      } else if (this.current === "ellipse") {
        let radiusX = this.bounds.width;
        let radiusY = this.bounds.height;
        this.ctx.beginPath();
        this.ctx.ellipse(
          this.mousedown.x,
          this.mousedown.y,
          radiusX,
          radiusY,
          0,
          0,
          Math.PI * 2
        );
        if (flg) {
          this.ctx.fill();
        }
        this.ctx.stroke(); //////////////
      } else if (this.current === "triangle") {
        let polypoints = [
          { x: this.bounds.x + this.bounds.width / 2, y: this.bounds.y },
          { x: this.bounds.x, y: this.bounds.y + this.bounds.height },
          {
            x: this.bounds.x + this.bounds.width,
            y: this.bounds.y + this.bounds.height,
          },
        ];
        this.ctx.beginPath();
        this.ctx.beginPath();
        this.ctx.moveTo(polypoints[0].x, polypoints[0].y);
        //attach all the points of the triangle
        for (let i = 1; i < 3; i++) {
          this.ctx.lineTo(polypoints[i].x, polypoints[i].y);
        }
        this.ctx.closePath();
        if (flg) {
          this.ctx.fill();
        }
        this.ctx.stroke();
      }
    },
    //for any shape
    drawShapeAction(shape_) {
      this.ctx.strokeStyle = this.borderColor;
      this.ctx.lineWidth = this.linwidth;
      ////////////////////////////////////////////
      let flg =
        this.holdedColor != "#ffffff" &&
        (this.current === "move" ||
          (this.fixed != "" && this.shapeInActionLocation > -1));
      if (flg) {
        this.ctx.fillStyle = this.holdedColor;
      }
      if (shape_.type === "rectangle") {
        if (flg) {
          this.ctx.fillRect(
            shape_.dim.x,
            shape_.dim.y,
            shape_.dim.width,
            shape_.dim.height
          );
        }
        this.ctx.strokeRect(
          shape_.dim.x,
          shape_.dim.y,
          shape_.dim.width,
          shape_.dim.height
        );
        //color in 129////////////
      } else if (shape_.type === "square") {
        if (flg) {
          this.ctx.fillRect(
            shape_.mouse_down.x - shape_.dim.width,
            shape_.mouse_down.y - shape_.dim.width,
            shape_.dim.width * 2,
            shape_.dim.width * 2
          );
        }
        this.ctx.strokeRect(
          shape_.mouse_down.x - shape_.dim.width,
          shape_.mouse_down.y - shape_.dim.width,
          shape_.dim.width * 2,
          shape_.dim.width * 2
        );
      } else if (shape_.type === "line") {
        //Draw Line
        this.ctx.beginPath();
        this.ctx.moveTo(shape_.mouse_down.x, shape_.mouse_down.y);
        this.ctx.lineTo(shape_.mouse_up.x, shape_.mouse_up.y);
        this.ctx.stroke();
      } else if (shape_.type === "circle") {
        let radius = shape_.dim.width;
        this.ctx.beginPath();
        this.ctx.arc(
          shape_.mouse_down.x,
          shape_.mouse_down.y,
          radius,
          0,
          Math.PI * 2
        );
        if (flg) {
          this.ctx.fill();
        }
        this.ctx.stroke(); //////////////
      } else if (shape_.type === "ellipse") {
        let radiusX = shape_.dim.width;
        let radiusY = shape_.dim.height;
        this.ctx.beginPath();
        this.ctx.ellipse(
          shape_.mouse_down.x,
          shape_.mouse_down.y,
          radiusX,
          radiusY,
          0,
          0,
          Math.PI * 2
        );
        if (flg) {
          this.ctx.fill();
        }
        this.ctx.stroke(); //////////////
      } else if (shape_.type === "triangle") {
        let polypoints = [
          { x: shape_.dim.x + shape_.dim.width / 2, y: shape_.dim.y },
          { x: shape_.dim.x, y: shape_.dim.y + shape_.dim.height },
          {
            x: shape_.dim.x + shape_.dim.width,
            y: shape_.dim.y + shape_.dim.height,
          },
        ];
        this.ctx.beginPath();
        this.ctx.moveTo(polypoints[0].x, polypoints[0].y);
        //attach all the points of the triangle
        for (let i = 1; i < 3; i++) {
          this.ctx.lineTo(polypoints[i].x, polypoints[i].y);
        }
        this.ctx.closePath();
        if (flg) {
          this.ctx.fill();
        }
        this.ctx.stroke();
      }
    },
    //implement the property inside a shape
    colorShape: function(_shape) {
      //set color for fill
      //check shape dimensions
      if (_shape._color != "#ffffff") {
        this.ctx.fillStyle = _shape._color;
        if (_shape.type === "rectangle") {
          this.ctx.fillRect(
            _shape.dim.x,
            _shape.dim.y,
            _shape.dim.width,
            _shape.dim.height
          );
        } else if (_shape.type === "square") {
          this.ctx.fillRect(
            _shape.mouse_down.x - _shape.dim.width,
            _shape.mouse_down.y - _shape.dim.width,
            _shape.dim.width * 2,
            _shape.dim.width * 2
          );
        } else if (_shape.type === "circle") {
          let radius = _shape.dim.width;
          this.ctx.beginPath();
          this.ctx.arc(
            _shape.mouse_down.x,
            _shape.mouse_down.y,
            radius,
            0,
            Math.PI * 2
          );
          this.ctx.fill();
        } else if (_shape.type === "ellipse") {
          let radiusX = _shape.dim.width;
          let radiusY = _shape.dim.height;
          this.ctx.beginPath();
          this.ctx.ellipse(
            _shape.mouse_down.x,
            _shape.mouse_down.y,
            radiusX,
            radiusY,
            0,
            0,
            Math.PI * 2
          );
          this.ctx.fill();
        } else if (_shape.type === "triangle") {
          let polypoints = [
            { x: _shape.dim.x + _shape.dim.width / 2, y: _shape.dim.y },
            { x: _shape.dim.x, y: _shape.dim.y + _shape.dim.height },
            {
              x: _shape.dim.x + _shape.dim.width,
              y: _shape.dim.y + _shape.dim.height,
            },
          ];
          this.ctx.beginPath();
          this.ctx.moveTo(polypoints[0].x, polypoints[0].y);
          //attach all the points of the triangle
          for (let i = 1; i < 3; i++) {
            this.ctx.lineTo(polypoints[i].x, polypoints[i].y);
          }
          this.ctx.closePath();
          this.ctx.fill();
        }
      }
    },
    SaveCanvasImage: function() {
      //Save image
      this.savedImageData = this.ctx.getImageData(
        0,
        0,
        this.canvasWidth,
        this.canvasHeight
      );
    },
    RedrawCanvasImage: function() {
      //Restore image
      this.ctx.putImageData(this.savedImageData, 0, 0);
    },
    ////////////////////////////////console
    setupCanvas: function() {
      //set all the stuff
      this.canvas = document.getElementById("my-canvas");
      this.ctx = this.canvas.getContext("2d");
      this.ctx.strokeStyle = this.borderColor;
      this.ctx.lineWidth = this.linwidth;
      this.canvas.addEventListener("mousedown", this.setMouseDown);
      this.canvas.addEventListener("mousemove", this.setMouseMove);
      this.canvas.addEventListener("mouseup", this.setMouseUp);
      this.fillColor = document.getElementById("colorPick");
      //start with initial value
      this.fillColor.value = "#ffffff";
      document.getElementById("colorPick").value = "#ffffff";
      this.fillColor.addEventListener("change", this._updateAll, false);
    },
  },
  //update color after dismiss the cursor
  _updateAll: function(event) {
    this.fillColor.value = event.target.value;
  },
  mounted() {
    document.addEventListener("DOMContentLoaded", this.setupCanvas);
    this.uploadedFile = document.getElementById("filePick");
    this.uploadedFile.addEventListener("change", (event) => {
      this.load(event);
    });
  },
};
</script>
<style scoped>
.box {
  height: 83px;
  width: 1330px;
  background-image: linear-gradient(
    rgb(160, 160, 160),
    rgb(142, 142, 142),
    rgb(132, 132, 132)
  );
  border-radius: 15px 30px 0px 0px;
  box-shadow: 6px 6px 6px rgb(240, 240, 240) inset;
  /*box-shadow:         inset 3px 3px 10px #ffffff;*/
  margin: auto;
}
.box-compon,
.box-compon-selected {
  position: relative;
  float: left;
  width: 5.36%;
  height: 82%;
  margin: 7px 6.3px;
  text-align: center;
  padding: 0px 0px;
  box-shadow: 5px 5px 3px rgb(152, 152, 152) inset;
  box-shadow: inset 0px 0px 7px #404040;
  cursor: pointer;
  outline: none;
  border-radius: 38px 38px;
}
.box-compon {
  background-color: rgb(255, 255, 255);
}
.box-compon:hover {
  background-color: rgb(171, 222, 228);
  transition: all 0.36s ease;
}
.box-compon-selected {
  background-color: rgb(171, 222, 228);
  box-shadow: 0px 0px 7px #7b7b7b;
}
#colorPick[type="color"] {
  position: relative;
  float: right;
  width: 2.3%;
  height: 45%;
  margin: 1.5% 1.36%;
  text-align: center;
  padding: 0px 0px;
}
#my-canvas {
  border: 5px solid rgb(132, 132, 132);
  border-radius: 0px 0px 10px 20px;
}
input[type="file"] {
  display: none;
}
.custom-file-upload {
  border: 1px solid #ccc;
  display: inline-block;
  padding: 6px 12px;
  cursor: pointer;
}
</style>

© 2020 GitHub, Inc. Terms Privacy Security Status Help Contact GitHub Pricing
API Training Blog About
