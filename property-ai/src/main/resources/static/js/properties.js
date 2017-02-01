var Property = React.createClass({

  getInitialState: function() {
    return {display: true };
  },
  handleDelete() {
    var self = this;
    $.ajax({
        url: self.props.property._links.self.href,
        type: 'DELETE',
        success: function(result) {
          self.setState({display: false});
        },
        error: function(xhr, ajaxOptions, thrownError) {
          toastr.error(xhr.responseJSON.message);
        }
    });
  },
  render: function() {

    if (this.state.display==false) return null;
    else return (
      <tr>
          <td>{this.props.property.date}</td>
          <td>{this.props.property.county}</td>
          <td>{this.props.property.price}</td>
          <td>
            <button className="btn btn-info" onClick={this.handleDelete}>Delete</button>
          </td>
      </tr>
    );
  }
});

var PropertyTable = React.createClass({

  render: function() {

    var rows = [];
    this.props.properties.forEach(function(property) {
      rows.push(
        <Property property={property} key={property.id} />);
    });

    return (
      <table className="table table-striped">
          <thead>
              <tr>
                  <th>Date of Sale</th>
                  <th>County</th>
                  <th>Price</th>
                  <th>Delete</th>
              </tr>
          </thead>
          <tbody>{rows}</tbody>
      </table>
    );
  }
});

var App = React.createClass({

  loadPropertiesFromServer: function() {

    var self = this;
    $.ajax({
        url: "http://localhost:8080/api/properties",
      }).then(function(data) {
        self.setState({ properties: data._embedded.properties });
      });

  },

  getInitialState: function() {
    return { properties: [] };
  },

  componentDidMount: function() {
    this.loadPropertiesFromServer();
  },

  render() {
    return ( <PropertyTable properties={this.state.properties} /> );
  }
});

ReactDOM.render(<App />, document.getElementById('properties') );