from flask import Flask,render_template
from flask.ext.bootstrap import Bootstrap
from flask import Flask
app = Flask(__name__)

@app.route("/")
def index():
	return render_template('index.html')

@app.route("/user/<name>")
def user(name):
	return render_template('user.html',name=name)

if __name__ == '__main__':
	bootstrap = Bootstrap(app)
	app.run(debug=True)