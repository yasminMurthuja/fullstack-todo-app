import { useEffect, useState } from "react";
import api from "../api/api";

export default function Todo(){
    const [todos, setTodos] = useState([]);
    const [title, setTitle] = useState("");
    const [editingTitle, setEditingTitle] = useState("");
    const [editingId, setEditingId] = useState(null);

    const loadTodos = async () =>{
        const res = await api.get("/todos");
        console.log("datas" ,res.data)
        setTodos(res.data);
        console.log("todos", todos);
    };
    useEffect(() => {
        loadTodos();
        console.log("useeffect", todos);
    },[]);

    const addTodo = async () =>{
        if(!title.trim()) return;

        await api.post("/todos", {title,completed: false});
        setTitle("");
        loadTodos();
    };

    const toggleComplete = async (todo) => {
        await api.put(`/todos/${todo.id}`,{
            title: todo.title,
            completed: !todo.completed
        })
        loadTodos();
    };

    const startEdit = (todo)=> {
        setEditingId(todo.id);
        setEditingTitle(todo.title);
    }

    const updateTodo = async (id) => {
        await api.put(`/todos/${id}`, {
            title: editingTitle,
            completed: todos.find(t => t.id === id).completed
        });

        setEditingId(null);
        setEditingTitle("");
        loadTodos();
    }

    const deleteTodo = async (id)=>{
        await api.delete(`/todos/${id}`);
        loadTodos();
    };

    return (
    <div className="min-h-screen bg-gradient-to-r from-indigo-500 to-purple-500 p-10">
      <div className="bg-white p-6 rounded-xl shadow-xl max-w-xl mx-auto animate-fade-in">
        <h1 className="text-2xl font-bold mb-4 text-center">My Todos</h1>

        {/* Add Todo */}
        <div className="flex gap-2 mb-4">
          <input
            className="border p-2 flex-1 rounded"
            placeholder="New todo..."
            value={title}
            onChange={(e) => setTitle(e.target.value)}
          />
          <button
            onClick={addTodo}
            className="bg-indigo-600 text-white px-4 rounded hover:bg-indigo-700"
          >
            Add
          </button>
        </div>

           {/* Todo List */}
        <ul className="space-y-2">
          {todos.map((todo) => (
            <li
              key={todo.id}
              className="flex items-center justify-between bg-gray-100 p-3 rounded"
            >
              <div className="flex items-center gap-2 flex-1">
                <input
                  type="checkbox"
                  checked={todo.completed}
                  onChange={() => toggleComplete(todo)}
                />

                {editingId === todo.id ? (
                  <input
                    className="border p-1 flex-1 rounded"
                    value={editingTitle}
                    onChange={(e) => setEditingTitle(e.target.value)}
                  />
                ) : (
                  <span
                    className={`flex-1 ${
                      todo.completed ? "line-through text-gray-500" : ""
                    }`}
                  >
                    {todo.title}
                     </span>
                )}
              </div>

              <div className="flex gap-2">
                {editingId === todo.id ? (
                  <button
                    onClick={() => updateTodo(todo.id)}
                    className="text-green-600 font-semibold"
                  >
                    Save
                  </button>
                ) : (
                  <button
                    onClick={() => startEdit(todo)}
                    className="text-blue-600"
                  >
                    Edit
                  </button>
                )}

                <button
                  onClick={() => deleteTodo(todo.id)}
                  className="text-red-500"
                >
                  Delete
                </button>
                 </div>
            </li>
          ))}
        </ul>
      </div>
    </div>
  );
  
}