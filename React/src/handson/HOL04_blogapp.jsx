import { Component } from "react";

class Post extends Component {
  render() {
    return <li>{this.props.title}</li>;
  }
}

export default class HOL04_blogapp extends Component {
  constructor(props) {
    super(props);
    this.state = {
      posts: [],
      title: "",
      hasError: false,
    };
  }

  componentDidMount() {
    console.log("Blog App Mounted");
  }

  componentDidCatch(error, info) {
    console.error(error, info);
    this.setState({ hasError: true });
  }

  handleChange = (e) => {
    this.setState({ title: e.target.value });
  };

  addPost = () => {
    if (this.state.title.trim() === "") return;

    const newPost = {
      id: this.state.posts.length + 1,
      title: this.state.title,
    };

    this.setState({
      posts: [...this.state.posts, newPost],
      title: "",
    });
  };

  render() {
    if (this.state.hasError) {
      return <p>Something went wrong.</p>;
    }

    return (
      <div>
        <h2>Blog App</h2>

        <input
          type="text"
          placeholder="Enter Blog Title"
          value={this.state.title}
          onChange={this.handleChange}
        />

        <button onClick={this.addPost}>Add Post</button>

        <h3>Blog Posts</h3>
        <ul>
          {this.state.posts.map((post) => (
            <Post key={post.id} title={post.title} />
          ))}
        </ul>
      </div>
    );
  }
}
